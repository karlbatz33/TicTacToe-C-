/********************************************************/
/*				NOTES FOR ROMANIAN TASK					*/
/********************************************************/
	
	1. All Cities are Case sensitive and should be entered with a capital letter 
	at the start of the name.
	
	2. The Romanian city 'Rimnicu Vilcea' should be typed in with an UNDERSCORE
	between the two words. 'Rimnicu_Vilcea' is appropriate input what is stored 
	in the string array of cities. 
	[This is because there are two names seperated which identify 1 city and the program
	will interpret it as two inputs. for example "Rimnicu" as the source location and
	"Vilcea" as the destination which will cause an error as it won't retrieve it from
	cities[] array]
	
	3. There is an extra node/city called 'dummyTestNode' which was used to test
	the code and lives in index 0 of cities[] string array. This city is ONLY connected
	to 'Arad' and can be ignored as it was only used for testing. In the map I have in
	the folder, you can see I added the node in the diagram as I was using this map as
	visual assistance while coding the program.