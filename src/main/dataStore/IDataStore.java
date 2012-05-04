package main.dataStore;

import main.entity.Informational;

public interface IDataStore {
	Informational getInformation(String filePath);
}
