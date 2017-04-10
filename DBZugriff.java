package package_710_MitarbeiterMitDB;

import com.db4o.*;

public class DBZugriff 
{
	ObjectContainer dbVerbindung;
	
	public ObjectContainer oeffneDbVerbindung ()
	{
		dbVerbindung = Db4o.openFile ("Mitarbeiter.yap");
		return dbVerbindung;
	}
	public void schliesseDbVerbindung ()
	{
		dbVerbindung.close ();
	}	
	
	public void schreibeObjekt (Object pObject)
	{
		dbVerbindung.set (pObject);
	}
	
	public Mitarbeiter leseObjekt (int pMitarbeiterID)
	{
		ObjectSet ergebnis;
		Mitarbeiter ausgabeMitarbeiter;
		
		Mitarbeiter vergleichsKonto = new Mitarbeiter (pMitarbeiterID);
		ergebnis = dbVerbindung.get (vergleichsKonto);
		
		if (ergebnis.hasNext () )
		{
			ausgabeMitarbeiter = (Mitarbeiter) ergebnis.next ();
		}
		else
		{
			ausgabeMitarbeiter = null;
		}
		return ausgabeMitarbeiter;
		
	}
	
	public void loescheKonto (int pMitarbeiterID)
	{
		dbVerbindung.delete(leseObjekt(pMitarbeiterID));
	}
	
	
}
