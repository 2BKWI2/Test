package package_710_MitarbeiterMitDB;

public class Mitarbeiter 
{
	public int mitarbeiterID;
	public String name;
	public String abteilung;
	public double gehalt;
	public double steuer;
	public boolean familienstand;
	
	public Mitarbeiter (int pMitarbeiterID)
	{
		mitarbeiterID = pMitarbeiterID;
	}
	
	public void setMitarbeiterID (int pMitarbeiterID)
	{
		mitarbeiterID = pMitarbeiterID;
	}
	public int getMitarbeiterID ()
	{
		return mitarbeiterID;
	}
	
	public void setName (String pName)
	{
		name = pName;
	}
	public String getName ()
	{
		return name;
	}
	
	public void setAbteilung (String pAbteilung)
	{
		abteilung = pAbteilung;		
	}
	public String getAbteilung ()
	{
		return abteilung;
	}
	
	public void setGehalt (double pGehalt)
	{
		gehalt = pGehalt;
	}
	public double getGehalt ()
	{
		return gehalt;
	}
	
	public void setSteuer (double pSteuer)
	{
		steuer = pSteuer;
	}
	public double getSteuer ()
	{
		return steuer;
	}
	
	public void setFamilienstand (boolean pFamilienstand)
	{
		familienstand = pFamilienstand;
	}
	public boolean getFamilienstand ()
	{
		return familienstand;
	}
	
	public void steuerBerechnenVerheiratet ()
	{
			if (gehalt <= 2000)
			{
				steuer = gehalt * 0.15;
			}
			else if (gehalt > 2000 && gehalt <= 3000)
			{
				steuer = gehalt * 0.30;
			}
			else if (gehalt > 3000 && gehalt <= 4000)
			{
				steuer = gehalt * 0.44;
			}
			else
			{
				steuer = gehalt * 0.50; 
			}
	}
	
	public void steuerBerechnenLedig ()
	{
		if (gehalt <= 2000)
		{
			steuer = gehalt * 0.2;
		}
		else if (gehalt > 2000 && gehalt <= 3000)
		{
			steuer = gehalt * 0.35;
		}
		else if (gehalt > 3000 && gehalt <= 4000)
		{
			steuer = gehalt * 0.49;
		}
		else
		{
			steuer = gehalt * 0.55; 
		}
	}
}
