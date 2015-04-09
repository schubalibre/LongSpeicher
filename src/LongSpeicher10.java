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

class LongSpeicher10 implements LongSpeicher {
   // ---------------------------------------------------------------------
   // Zum Ein-/Ausschalten von Testbefehlen:
   static final boolean TST1 = true;
   // ---------------------------------------------------------------------
   private long[] speicher;
   private int    nfi = 0; // naechster freier Index

   public LongSpeicher10(int groesse) {
      speicher = new long[groesse];
   }
   // ---------------------------------------------------------------------
   private int index(long n) {
	   
	   for(int i = 0; i < speicher.length; i++){
		   if(speicher[i] == n) 
			   return i;
	   }
	  // Liefert den kleinsten Index i fuer den gilt: speicher[i] == n
	  // oder -1, wenn n nicht im speicher ist.
	  return -1;
   }
   // ---------------------------------------------------------------------
   public boolean fuegeEin(long n) {
	   
	   if(nfi < speicher.length){
		   speicher[nfi] = n;
		   nfi++;
		   return true;
	   }
	   
      // Fuegt n in diesen Speicher ein und liefert true.
      // Liefert false, wenn n nicht eingefuegt werden konnte.

      return false;
   }
   // ---------------------------------------------------------------------
   public boolean loesche(long n) {
	   
	   for(int i = 0; i < speicher.length; i++){
		   if(speicher[i] == n){
			   speicher[i] = 0;
		   		nfi--;
		   		return true;
		   }
	   }
	   
      // Loescht ein Vorkommen von n in diesem Speicher, und liefert true.
      // Liefert false falls n nicht in diesem Speicher vorkommt.

      return false;
   }
   // ---------------------------------------------------------------------
   public boolean istDrin(long n) {
      // Liefert true genau dann wenn n in diesem Speicher vorkommt.

      return false; // MUSS ERSETZT WERDEN
   }
   // ---------------------------------------------------------------------
   // Zum Testen:
   private void print() {
      // Gibt diesen Speicher in lesbarer Form zur Standardausgabe aus:
      printf("nfi: %d, speicher: %s%n", nfi, Arrays.toString(speicher));
   }
   // ---------------------------------------------------------------------
   static public void main(String[] args) {
      printf("LongSpeicher10: Jetzt geht es los!%n");
      printf("----------------------------------%n");

      LongSpeicher10 lsa = new LongSpeicher10(5);

      lsa.print();
      lsa.fuegeEin(10);
      lsa.print();
      lsa.istDrin(10);
      lsa.istDrin(20);

      printf("----------------------------------%n");
      printf("LongSpeicher10: Das war's erstmal!%n");
   } // main
   // ---------------------------------------------------------------------
   // Eine Methode mit einem kurzen Namen:
   static void printf(String f, Object... v) {System.out.printf(f, v);}
   // ---------------------------------------------------------------------
} // class LongSpeicher10
