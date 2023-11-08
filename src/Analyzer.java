//=============================================================================================================================================================
//
// IMPLEMENT THIS CLASS HOWEVER YOU WANT, BUT DO NOT CHANGE THE SIGNATURES OF Analyzer(), addEntry(), or printResults(). YOU MUST USE addEntry() TO GET THE
// DATA; DO NOT ACCESS OTHER CLASSES IN THE PROJECT (UNLESS THEY ARE YOURS).
//
public class Analyzer
{
   double xBodyAInitial = 0;
   double xBodyBInitial = 0;
   double yBodyAInitial = 0;
   double yBodyBInitial = 0;
   double xBodyAFinal = 0;
   double yBodyAFinal = 0;
   double xBodyBFinal = 0;
   double yBodyBFinal = 0;
   double xTotalBodyA = 0;
   double xTotalBodyB = 0;
   double yTotalBodyA = 0;
   double yTotalBodyB = 0;
   double xBodyAPrevious = 0;
   double xBodyBPrevious = 0;
   double yBodyAPrevious = 0;
   double yBodyBPrevious = 0;
   double vxBodyA = 0;
   double vxBodyB = 0;
   double vyBodyA = 0;
   double vyBodyB = 0;
   double vxBodyAMin = Double.MAX_VALUE;
   double vxBodyAMax = -1;
   double vxBodyBMin = Double.MAX_VALUE;
   double vxBodyBMax = -1;
   double vyBodyAMin = Double.MAX_VALUE;
   double vyBodyAMax = -1;
   double vyBodyBMin = Double.MAX_VALUE;
   double vyBodyBMax = -1;
   long maxStep = 0;
   String bodyAID = "";
   String bodyBID = "";
   double finishingTimeBodyA = 0.0;
   double finishingTimeBodyB = 0.0;
   boolean bodySwitch = true;
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public Analyzer()
   {

   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public void addEntry(final long step, final double time, final String id, final double x, final double y)
   {
      //System.out.println("<log>: "+ step + "," + time + "," + id + "," + x + "," + y);
      if(bodyAID == "")
      {
         bodyAID = id;
         xBodyAInitial = x;
         yBodyAInitial = y;
         xBodyAPrevious = x;
         yBodyAPrevious = y;
      }
      if(bodyBID == "")
      {
         bodyBID = id;
         xBodyBInitial = x;
         yBodyBInitial = y;
         xBodyBPrevious = x;
         yBodyBPrevious = y;
      }
      if(bodySwitch)
      {
         xTotalBodyA += x;
         yTotalBodyA += y;
         vxBodyA = Math.abs(xBodyAPrevious - x);
         vyBodyA = Math.abs(yBodyAPrevious - y);
         if(vxBodyA < vxBodyAMin)
         {
            vxBodyAMin = vxBodyA;
         }
         if(vyBodyA < vyBodyAMin)
         {
            vyBodyAMin = vyBodyA;
         }
         if(vxBodyA > vxBodyAMax)
         {
            vxBodyAMax = vxBodyA;
         }
         if(vyBodyA > vyBodyAMax)
         {
            vyBodyAMax = vyBodyA;
         }
         xBodyAFinal = x;
         yBodyAFinal = y;
         if(finishingTimeBodyA < time)
         {
            finishingTimeBodyA = time;
         }
         bodySwitch = !bodySwitch;
      }
      else
      {
         xTotalBodyB += x;
         yTotalBodyB += y;
         vxBodyB = Math.abs(xBodyBPrevious - x);
         vyBodyB = Math.abs(yBodyBPrevious - y);
         if(vxBodyB < vxBodyBMin)
         {
            vxBodyBMin = vxBodyB;
         }
         if(vyBodyB < vyBodyBMin)
         {
            vyBodyBMin = vyBodyB;
         }
         if(vxBodyB > vxBodyBMax)
         {
            vxBodyBMax = vxBodyB;
         }
         if(vyBodyB > vyBodyBMax)
         {
            vyBodyBMax = vyBodyB;
         }
         xBodyBFinal = x;
         yBodyBFinal = y;
         if(finishingTimeBodyB < time)
         {
            finishingTimeBodyB = time;
         }
         bodySwitch = !bodySwitch;
      }
      if(maxStep < step)
      {
         maxStep = step;
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   public void printResults()
   {
      System.out.println("Body: " + bodyAID);
      System.out.println("\nTime: " + finishingTimeBodyA);
      System.out.println("\nDistance: " + (Math.sqrt((Math.exp(xBodyAInitial - xBodyAFinal) + Math.exp(yBodyAInitial - yBodyAFinal)))));
      System.out.println("\nVelocity");
      System.out.println("  min: " + Math.sqrt(Math.exp(vxBodyAMin) + Math.exp(vyBodyAMin)));
      System.out.println("  max: " + Math.sqrt(Math.exp(vxBodyAMax) + Math.exp(vyBodyAMax)));
      System.out.println("  avg: " + "");
   }
}
