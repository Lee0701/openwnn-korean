package me.blog.hgl1002.openwnn.KOKR.inputmethod.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for jamo handler.
 * Processes Hangul Jamos.
 */
public class JamoHandler {

	protected CombinationTable combinationTable;
	protected VirtualJamoTable virtualJamoTable;

	public CombinationTable getCombinationTable() {
		return combinationTable;
	}

	public void setCombinationTable(CombinationTable combinationTable) {
		this.combinationTable = combinationTable;
	}

	public VirtualJamoTable getVirtualJamoTable() {
		return virtualJamoTable;
	}

	public void setVirtualJamoTable(VirtualJamoTable virtualJamoTable) {
		this.virtualJamoTable = virtualJamoTable;
	}

	/**
	 * Class for combination table.
	 * Rules list for combining two Hangul Jamo into one.
	 */
	public static class CombinationTable {

		/**
		 * Lits of combinations.
		 */
		List<Combination> choCombinations, jungCombinations, jongCombinations;

		public CombinationTable() {
			this.choCombinations = new ArrayList<>();
			this.jungCombinations = new ArrayList<>();
			this.jongCombinations = new ArrayList<>();
		}

		public List<Combination> getChoCombinations() {
			return choCombinations;
		}

		public void setChoCombinations(List<Combination> choCombinations) {
			this.choCombinations = choCombinations;
		}

		public List<Combination> getJungCombinations() {
			return jungCombinations;
		}

		public void setJungCombinations(List<Combination> jungCombinations) {
			this.jungCombinations = jungCombinations;
		}

		public List<Combination> getJongCombinations() {
			return jongCombinations;
		}

		public void setJongCombinations(List<Combination> jongCombinations) {
			this.jongCombinations = jongCombinations;
		}

		/**
		 * Class for combination.
		 */
		public static class Combination {
			protected int a, b, result;

			public Combination(int a, int b, int result) {
				this.a = a;
				this.b = b;
				this.result = result;
			}

			public int getA() {
				return a;
			}

			public int getB() {
				return b;
			}

			public int getResult() {
				return result;
			}
		}
	}

	/**
	 * Class for virtual jamo table.
	 * A rule list for making specified virtual jamo look like another jamo while composing.
	 */
	public static class VirtualJamoTable {

		/**
		 * List of virtual jamos.
		 */
		List<VirtualJamo> choVirtuals, jungVirtuals, jongVirtuals;

		public VirtualJamoTable() {
			this.choVirtuals = new ArrayList<>();
			this.jungVirtuals = new ArrayList<>();
			this.jongVirtuals = new ArrayList<>();
		}

		/**
		 * Class for virtual jamo.
		 * {@link #virtual} is to be handled to look like {@link #real} while being composed.
		 */
		public static class VirtualJamo {

			/**
			 * A virtual jamo code.
			 */
			protected int virtual;
			/**
			 * A real code for {@link #virtual} to look like.
			 */
			protected int real;

			public VirtualJamo(int virtual, int real) {
				this.virtual = virtual;
				this.real = real;
			}

			public int getVirtual() {
				return virtual;
			}

			public int getReal() {
				return real;
			}
		}
	}

}
