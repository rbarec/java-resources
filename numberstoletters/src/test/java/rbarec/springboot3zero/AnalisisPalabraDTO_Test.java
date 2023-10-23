package rbarec.springboot3zero;

import rbarec.numberstoletters.domain.Palabra;

public class AnalisisPalabraDTO_Test {

	public static void main(String[] args) {
		System.out.println((new Palabra(1, "0922067715:")).lightLog());
		//----
		System.out.println((new Palabra(1, "Ronald")).lightLog());
		System.out.println((new Palabra(1, "Ronald:")).lightLog());
		System.out.println((new Palabra(1, "Ronald,")).lightLog());
		System.out.println((new Palabra(1, "Ronald*")).lightLog());
		System.out.println((new Palabra(1, "Ronald/")).lightLog());
		System.out.println((new Palabra(1, "Ronald?")).lightLog());
		System.out.println((new Palabra(1, "De")).lightLog());
		System.out.println((new Palabra(1, "1.-")).lightLog());
		System.out.println((new Palabra(1, "0922067715")).lightLog());
		System.out.println((new Palabra(1, "0922067715:")).lightLog());
		System.out.println((new Palabra(1, "0922067715001")).lightLog());
		System.out.println((new Palabra(1, "1234")).lightLog());
		System.out.println((new Palabra(1, "1234.00")).lightLog());
		System.out.println((new Palabra(1, "$1234.00")).lightLog());
		System.out.println((new Palabra(1, "$1234,00")).lightLog());
		System.out.println((new Palabra(1, "$1.234,00")).lightLog());
		System.out.println((new Palabra(1, "1234,00:")).lightLog());
		System.out.println((new Palabra(1, "1234000012340000")).lightLog());

	}
}
