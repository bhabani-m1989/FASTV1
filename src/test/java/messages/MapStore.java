package messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStore {
	
	 HashMap<String,List<String>> str;
		
		public MapStore()
		{
			str	=new HashMap<String,List<String>>();
		}
		
		public  void storeData(String name,List<String> l)
		{

		str.put(name, l);
	}
		
		public  Map<String,List<String>> mydata()
		{
			return str;
		}
		


}
