package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class FightResolver {
	FIS fis;
	public FightResolver() {
	      // Load from 'FCL' file
        String fileName = "fcl/fight.fcl";
        fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

       
	}
	
	public double resolveFight(double opponent, double mana){
		fis.setVariable("opponent", opponent);
        fis.setVariable("mana", mana);
        fis.evaluate(); 
        //JFuzzyChart.get().chart(fis);
        return fis.getVariable("victory").getValue();
	}
}
