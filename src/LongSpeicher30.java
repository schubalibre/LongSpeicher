// Datei LongSpeicher10.java
/* ------------------------------------------------------------------------
Jedes Objekt der Klasse LongSpeicher30 ist ein Speicher, in dem man
long-Werte sammeln (einfuegen, entfernen, suchen) kann.
"Doppelgaenger" sind erlaubt.
---------------------------------------------------------------------------
Implementierung: Als unsortierte Reihung
------------------------------------------------------------------------ */
import java.util.Arrays; // Fuer Testausgaben
import java.util.List;

class LongSpeicher30 implements LongSpeicher {
	
	// ---------------------------------------------------------------------
   // Zum Ein-/Ausschalten von Testbefehlen:
   static final boolean TST1 = true;
   // ---------------------------------------------------------------------
   // Eine (statische) geschachtelte Klasse (nested static class).
   // Jedes Objekt dieser Klasse kann als Knoten einer einfach
   // verketteten Liste verwendet werden:
   static protected class Knoten {
      Knoten next;
      long   data;

      Knoten(Knoten next, long data) {           // Konstruktor
         this.next = next;
         this.data = data;
      }
   }
   // ---------------------------------------------------------------------
   // Eine leere Liste besteht aus 2 Dummy-Knoten:
   // einem End-Dummy-Knoten EDK und einem Anfangs-Dummy-Knoten ADK. Die
   // "richtigen Knoten" werden spaeter zwischen die 2 Dummies gehaengt.
   final Knoten EDK = new Knoten(null, 0); // End-Dummy-Knoten
   final Knoten ADK = new Knoten(EDK,  0); // Anfangs-Dummy-Knoten
   // ---------------------------------------------------------------------
   private Knoten vorgaenger(long n) {
	   // Liefert den Vorgaenger eines Knotens, der n enthaelt, oder
	   // den Vorgaenger des EDK (falls n in dieser Liste nicht vorkommt).
	   EDK.data = n;
	   Knoten hier = ADK;
	   while(hier.next.data != n){
		   hier = hier.next;
	   }
	   return hier;
   }

   // ---------------------------------------------------------------------
   public boolean fuegeEin(long n) {
	   
	  // Fuegt n in diesen Speicher ein und liefert true.
	  // Liefert false, wenn n nicht eingefuegt werden konnte.
	  ADK.next = new Knoten(ADK.next, n); 
	  return true;
   }
   // ---------------------------------------------------------------------
   public boolean loesche(long n) {
	   Knoten vor = vorgaenger(n);
	   if(vor.next == EDK) return false;
	   
	   vor.next = vor.next.next;
	   return true;
		
      // Loescht ein Vorkommen von n in diesem Speicher, und liefert true.
      // Liefert false falls n nicht in diesem Speicher vorkommt.
   }
   // ---------------------------------------------------------------------
   public boolean istDrin(long n) {
      // Liefert true genau dann wenn n in diesem Speicher vorkommt.
      return (vorgaenger(n).next != EDK);
   }
   // ---------------------------------------------------------------------
   // Zum Testen:
   private void print() {
      // Gibt diesen Speicher in lesbarer Form zur Standardausgabe aus:
//      printf("nfi: %d, speicher: %s%n", nfi, Arrays.toString(speicher));
   }
   // ---------------------------------------------------------------------
   static public void main(String[] args) {
      printf("LongSpeicher10: Jetzt geht es los!%n");
      printf("----------------------------------%n");

      LongSpeicher30 lsa = new LongSpeicher30();

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
