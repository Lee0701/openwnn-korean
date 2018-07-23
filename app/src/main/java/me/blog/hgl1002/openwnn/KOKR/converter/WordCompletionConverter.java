package me.blog.hgl1002.openwnn.KOKR.converter;

import android.os.AsyncTask;
import android.os.Build;

import org.greenrobot.eventbus.EventBus;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.blog.hgl1002.openwnn.KOKR.ComposingWord;
import me.blog.hgl1002.openwnn.KOKR.EngineMode;
import me.blog.hgl1002.openwnn.KOKR.WordConverter;
import me.blog.hgl1002.openwnn.KOKR.trie.Dictionaries;
import me.blog.hgl1002.openwnn.KOKR.trie.TrieDictionary;
import me.blog.hgl1002.openwnn.event.DisplayCandidatesEvent;

public class WordCompletionConverter implements WordConverter {

	private WordCompletionConvertTask task;
	private TrieDictionary dictionary;
	private Map<Character, String> keyMap;

	@Override
	public void convert(ComposingWord word) {
		if(task != null) task.cancel(true);
		if(word.length() <= 0) return;
		task = new WordCompletionConvertTask(word, keyMap, dictionary);
		task.execute();
	}

	@Override
	public void setEngineMode(EngineMode engineMode) {
		this.dictionary = Dictionaries.getDictionary(engineMode.properties.languageCode, 0);
		keyMap = new HashMap<>();
	}

	private static class WordCompletionConvertTask extends AsyncTask<Void, Integer, Integer> {
		private ComposingWord word;
		private Map<Character, String> keyMap;
		private TrieDictionary dictionary;
		private List<String> result;

		WordCompletionConvertTask(ComposingWord word, Map<Character, String> keyMap, TrieDictionary dictionary) {
			this.word = word;
			this.keyMap = keyMap;
			this.dictionary = dictionary;
		}

		public void execute() {
			if(Build.VERSION.SDK_INT >= 11) {
				super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			} else {
				super.execute();
			}
		}

		@Override
		protected Integer doInBackground(Void... voids) {
			String word = Normalizer.normalize(this.word.getEntireWord(), Normalizer.Form.NFKD);
			if(dictionary != null && dictionary.isReady()) {
				this.result = dictionary.searchStartsWith(word, word.length() * 2);
				return 1;
			}
			return -1;
		}

		@Override
		protected void onPostExecute(Integer integer) {
			super.onPostExecute(integer);
			if(integer == 1) {
				if(this.result == null) return;
				EventBus.getDefault().post(new DisplayCandidatesEvent(result));
			}
		}

	}

}
