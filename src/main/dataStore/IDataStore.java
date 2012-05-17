package main.dataStore;

import main.entity.Informational;

public interface IDataStore {
	Informational getInformation() throws Exception;
	Parameters getParameters();
	void setParameters(Parameters parameters);
}
