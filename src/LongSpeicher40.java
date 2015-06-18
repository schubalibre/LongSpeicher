// Datei LongSpeicher10.java
/* ------------------------------------------------------------------------
Jedes Objekt der Klasse LongSpeicher40 ist ein Speicher, in dem man
long-Werte sammeln (einfuegen, entfernen, suchen) kann.
"Doppelgaenger" sind erlaubt.
---------------------------------------------------------------------------
Implementierung: Als unsortierte Reihung
------------------------------------------------------------------------ */

class LongSpeicher40 implements LongSpeicher {
	
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
	   while(hier.next.data < n) hier = hier.next;
	   
	   return hier;
   }

   // ---------------------------------------------------------------------
   public boolean fuegeEin(long n) {
	   
	  // Fuegt n in diesen Speicher ein und liefert true.
	  // Liefert false, wenn n nicht eingefuegt werden konnte.
	  Knoten vor = vorgaenger(n);
	  vor.next = new Knoten(vor.next, n); 
	  
	  return true;
   }
   // ---------------------------------------------------------------------
   public boolean loesche(long n) {
	   Knoten vor = vorgaenger(n);
	   if(vor.next.data != n || vor.next == EDK) return false;
	   
	   vor.next = vor.next.next;
	   
	   return true;
		
      // Loescht ein Vorkommen von n in diesem Speicher, und liefert true.
      // Liefert false falls n nicht in diesem Speicher vorkommt.
   }
   // ---------------------------------------------------------------------
   public boolean istDrin(long n) {
      // Liefert true genau dann wenn n in diesem Speicher vorkommt.
	  Knoten vor = vorgaenger(n);
      return (vor.next.data == n && vor.next != EDK);
   }
   // ---------------------------------------------------------------------
   // Zum Testen:
   private void print() {
      // Gibt diesen Speicher in lesbarer Form zur Standardausgabe aus:
//      printf("nfi: %d, speicher: %s%n", nfi, Arrays.toString(speicher));
   }
   
   public String toString(){
	   
	   if(ADK.next == EDK) return "[]";
	   
	   StringBuilder s = new StringBuilder("[" + ADK.next.data);
	   
	   Knoten hier = ADK.next.next;
	   
	   while(hier != EDK){
		   s.append(", " + hier.data);
		   hier = hier.next;
	   }
	   
	   s.append("]");

	   
	    return s.toString();
   }
   // ---------------------------------------------------------------------
   static public void main(String[] args) {
      printf("LongSpeicher10: Jetzt geht es los!%n");
      printf("----------------------------------%n");

      LongSpeicher40 lsa = new LongSpeicher40();

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

