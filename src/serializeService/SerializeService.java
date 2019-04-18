package serializeService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import machine.abstraction.StateMachine;

public class SerializeService {
	private String homeDirectory;
	private String path;
	
	public SerializeService() {
		homeDirectory = System.getProperty("user.dir");
	}
	
	public boolean serialize(String machineName, StateMachine toSerialize) {
		if(toSerialize == null) {
			System.out.println("In order to serialize an object it must exist");
			return false;
		}
		path = homeDirectory + "\\" + machineName + ".ser";
		return writeToFile(toSerialize);
	}
	
	private boolean writeToFile(StateMachine toSerialize) {
		try {
	          FileOutputStream fileOut = new FileOutputStream(path);
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(toSerialize);
	          out.close();
	          fileOut.close();
	          System.out.println("state machine saved in " + path);
	          return true;
		    } catch (IOException i) {
		       i.printStackTrace();
		    }
		return false;
	}

	public StateMachine deserialize(String machineName) {
		path = homeDirectory + "\\" + machineName + ".ser";
		File savedMachineFile = new File(path);
		if(savedMachineFile.isFile() && savedMachineFile.canRead())
			 return extractDataFromDisc(savedMachineFile, machineName);
		return null;
	}

	private StateMachine extractDataFromDisc(File savedMachineFile, String machineName) {
		 try {
	         FileInputStream fileIn = new FileInputStream(savedMachineFile);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         StateMachine machine = (StateMachine) in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("state machine " + machineName + " has been loaded");
	         return machine;
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("StateMachine class not found");
	         c.printStackTrace();
	      }		
		  return null;
	}
}
