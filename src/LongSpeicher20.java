// Datei LongSpeicher10.java
/* ------------------------------------------------------------------------
 Jedes Objekt der Klasse LongSpeicher10 ist ein Speicher, in dem man
 long-Werte sammeln (einfuegen, entfernen, suchen) kann.
 "Doppelgaenger" sind erlaubt.
 ---------------------------------------------------------------------------
 Implementierung: Als unsortierte Reihung
 ------------------------------------------------------------------------ */
import java.util.Arrays; // Fuer Testausgaben
import java.util.List;

class LongSpeicher20 implements LongSpeicher {
	// ---------------------------------------------------------------------
	// Zum Ein-/Ausschalten von Testbefehlen:
	static final boolean TST1 = true;
	// ---------------------------------------------------------------------
	private long[] speicher;
	private int nfi = 0; // naechster freier Index

	public LongSpeicher20(int groesse) {
		speicher = new long[groesse];
	}

	// ---------------------------------------------------------------------
	private int index(long n) {
		// Liefert den Index i, an dem n steht oder eingefuegt werden sollte.

		// Binaer gesucht wird jeweils
		// in der Teilreihung speicher[von..bis]:
		int von = 0;
		int bis = nfi - 1;

		while (von <= bis) {
			int mitte = von + (bis - von) / 2;

			if (n > speicher[mitte]) {
				von = mitte+1;
			} else if (n < speicher[mitte]) {
				bis = mitte-1;
			} else {
				return mitte;
			}
		}
		return von; // n steht nicht im speicher
	}

	// ---------------------------------------------------------------------
	public boolean fuegeEin(long n) {

		// Fuegt n in diesen Speicher ein und liefert true.
		// Liefert false, wenn n nicht eingefuegt werden konnte.

		if (nfi >= speicher.length)
			return false;
		
		if (this.istDrin(n))
			return false;
		
		// wir holen uns den neuen Index
		int newIndex = this.index(n);
		
		// liegt der Index innerhalb des Arrays müssen wir die enthaltenen Zahlen um eine Stelle nach hinten verändern
		// um die Werte nicht zu überschreiben, beginnen wir von hinten an zu ändern
		// falls es nicht innerhalb des Arrays liegt wird die Schleife nicht benutzt 
		for(int i = nfi; i > newIndex; i--){
			speicher[i] = speicher[i-1];
		}
		// danach setzten wir in die entstandene Lücke unseren neuen Wert ein
		speicher[newIndex] = n;
		// nfi eins höher
		nfi++;
		return true;
	}

	// ---------------------------------------------------------------------
	public boolean loesche(long n) {
		
		
		if (nfi <= 0)
			return false;
		
		int index = this.index(n);
		
		if(index > speicher.length)
			return false;
		
		if (speicher[index] == n) {
System.out.println(index);
System.out.println(nfi);
System.out.println(speicher[index]);
			for(int i = index; i < nfi; i++){
				speicher[i] = speicher[i+1];
			}
			speicher[nfi] = 0;
			nfi--;
			return true;
		}
		return false;

		// Loescht ein Vorkommen von n in diesem Speicher, und liefert true.
		// Liefert false falls n nicht in diesem Speicher vorkommt.
	}

	// ---------------------------------------------------------------------
	public boolean istDrin(long n) {
		// Liefert true genau dann wenn n in diesem Speicher vorkommt.
		int i = index(n);
		
		if(i >= nfi)
			return false;
		
		return speicher[i] == n;
	}

	// ---------------------------------------------------------------------
	// Zum Testen:
	private void print() {
		// Gibt diesen Speicher in lesbarer Form zur Standardausgabe aus:
		printf("nfi: %d, speicher: %s%n", nfi, Arrays.toString(speicher));
	}

	// ---------------------------------------------------------------------
	static public void main(String[] args) {
		printf("LongSpeicher20: Jetzt geht es los!%n");
		printf("----------------------------------%n");

		LongSpeicher20 lsa = new LongSpeicher20(5);

		lsa.print();
		lsa.fuegeEin(3);
		lsa.fuegeEin(9);
		lsa.fuegeEin(6);
		lsa.fuegeEin(10);
		lsa.fuegeEin(9);
		lsa.fuegeEin(1);
		lsa.print();
		lsa.loesche(10);
		lsa.loesche(6);
		lsa.loesche(60);
		lsa.print();
	
		printf("----------------------------------%n");
		printf("LongSpeicher10: Das war's erstmal!%n");
	} // main
		// ---------------------------------------------------------------------
		// Eine Methode mit einem kurzen Namen:

	static void printf(String f, Object... v) {
		System.out.printf(f, v);
	}
	// ---------------------------------------------------------------------
} // class LongSpeicher10
