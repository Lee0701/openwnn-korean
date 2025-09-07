package me.blog.hgl1002.openwnn;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SebeolHangulIMEControlPanel extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(SebeolHangulIME.getInstance() == null) {
			new SebeolHangulIME(this);
		}
		setContentView(R.layout.activity_settings);
		if(savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.frame_layout, new HeadersFragment())
					.commit();
		}
		getSupportFragmentManager().addOnBackStackChangedListener(() -> {
			getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() != 0);
		});
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frame_layout), (OnApplyWindowInsetsListener) (view, windowInsets) -> {
			Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
			ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
			params.topMargin = insets.top;
			params.bottomMargin = insets.bottom;
			params.leftMargin = insets.left;
			params.rightMargin = insets.right;
			view.setLayoutParams(params);
			return WindowInsetsCompat.CONSUMED;
		});
    }

	@Override
	public boolean onPreferenceStartFragment(@NonNull PreferenceFragmentCompat caller, @NonNull Preference pref) {
		Bundle args = pref.getExtras();
		String fragmentClassName = pref.getFragment();
		Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), fragmentClassName);
		fragment.setArguments(args);
		fragment.setTargetFragment(caller, 0);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.frame_layout, fragment)
				.addToBackStack(null)
				.commit();
		setTitle(pref.getTitle());
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		if(getSupportFragmentManager().popBackStackImmediate()) {
			return true;
		}
		return super.onSupportNavigateUp();
	}

	public static class HeadersFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			if(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("reveal_dev_settings", false)) {
				setPreferencesFromResource(R.xml.openwnn_pref_ko_headers_dev, rootKey);
			} else {
				setPreferencesFromResource(R.xml.openwnn_pref_ko_headers, rootKey);
			}
		}
	}

	public static class InputMethodFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_method, rootKey);
		}
	}

	public static class KeyboardAppearanceFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_appearance, rootKey);
		}
	}

	public static class SoftKeyboardFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_softkeyboard, rootKey);
		}
	}

	public static class HardKeyboardFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_hardkeyboard, rootKey);
		}
	}

	public static class SystemFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_system, rootKey);
		}
	}

	public static class AboutFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_about, rootKey);
		}
	}

	public static class DeveloperFragment extends PreferenceFragmentCompat {
		@Override
		public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
			setPreferencesFromResource(R.xml.openwnn_pref_ko_developer, rootKey);
		}
	}

}
