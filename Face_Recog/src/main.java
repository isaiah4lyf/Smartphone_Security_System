import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.mathworks.engine.MatlabEngine;

public class main {
	private static String Matlab_Path_train;
	private static String Matlab_Path;

	private static MATEngines_Management MATEngines;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			MATEngines = new MATEngines_Management();
			MATEngines.Start_MATEngines();
			Scanner in = new Scanner(new FileReader("data/MATLAB_TRAIN_DATA/NAMES_AND_IDS.txt"));
			Matlab_Path_train  = new File(".").getCanonicalPath() + "/MATLAB_SCRIPTS";
		    Matlab_Path  = new File(".").getCanonicalPath() + "/data";
			
		    //Extract_Features(in,Matlab_Path_train,Matlab_Path);
		    
		    
			String[] Lines = new String[132];
			for(int i = 0; i < 132; i++)
			{
				Lines[i] = in.nextLine();
			}
		    ArrayList<Models> models = new ArrayList<Models>();
		    int index = 0;
		    for(int i = 0; i < 33; i++)
		    {
	    		Models model = new Models();
	    		model.setModel_ID(Lines[index].split(" ")[2]);
	    		model.setUser_1_ID(Lines[index].split(" ")[0]);
	    		model.setUser_1_Name(Lines[index].split(" ")[1]);
	    		
	    		index++;
	    		
	    		model.setUser_2_ID(Lines[index].split(" ")[0]);
	    		model.setUser_2_Name(Lines[index].split(" ")[1]);
	    		
	    		index++;
	    		
	    		model.setUser_3_ID(Lines[index].split(" ")[0]);
	    		model.setUser_3_Name(Lines[index].split(" ")[1]);
	    		
	    		index++;
	    		
	    		model.setUser_4_ID(Lines[index].split(" ")[0]);
	    		model.setUser_4_Name(Lines[index].split(" ")[1]);
	    		models.add(model);
	    		
	    		index++;
		    }
		    
		   
		    System.out.println("Starting all....");
		    for(int i = 0; i < 33; i++)
		    {
		    	Train_Test(models.get(i));
		    }
		    /*	
		    long startTime = System.currentTimeMillis();
		    for(int k = 16; k < 21; k++)
		    {
		    	System.out.println("Predicting With Image: " + k);
		    	System.out.println("-------------------------------");
			    for(int j = 0; j < 33; j++)
			    {
			    	
				    double[] array = Predict_User(models.get(j),"9338527."+k);
				    System.out.println("Model " + models.get(j).getModel_ID() + " Results...");
					double class_1 = 0;
					int class_1_Count = 0;
					double class_2 = 0;
					int class_2_Count = 0;
					double class_3 = 0;
					int class_3_Count = 0;
					double class_4 = 0;
					int class_4_Count = 0;
					for(int i = 0; i < array.length; i++)
					{
						if(array[i] == Double.parseDouble(models.get(j).getUser_1_ID()))
						{
							class_1_Count++;
							class_1 = array[i];
						}
						else if(array[i] == Double.parseDouble(models.get(j).getUser_2_ID()))
						{
							class_2_Count++;
							class_2 = array[i];
						}
						else if(array[i] == Double.parseDouble(models.get(j).getUser_3_ID()))
						{
							class_3_Count++;
							class_3 = array[i];
						}
						else if(array[i] == Double.parseDouble(models.get(j).getUser_4_ID()))
						{
							class_4_Count++;
							class_4 = array[i];
						}
					}
					System.out.println(class_1 + ": " + class_1_Count + " , " +((double)(class_1_Count/(double)array.length)*100) + "%");
					System.out.println(class_2 + ": " + class_2_Count + " , " +((double)(class_2_Count/(double)array.length)*100) + "%");
					System.out.println(class_3 + ": " + class_3_Count + " , " +((double)(class_3_Count/(double)array.length)*100) + "%");
					System.out.println(class_4 + ": " + class_4_Count + " , " +((double)(class_4_Count/(double)array.length)*100) + "%");
					System.out.println("Total: " + array.length);
			    }
			    System.out.println("-------------------------------");
		    }
		    long endTime = System.currentTimeMillis();
		    System.out.println("Prediction time: " + (endTime - startTime)/1000 + " sec");
		   	 */
		    in.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void Train_Test(Models model)
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					System.out.println("Searching MATEng....");
				    MatlabEngine mat  = MATEngines.Get_Free_MATEngi();
				    while(mat == null)
				    {
				    	mat  = MATEngines.Get_Free_MATEngi();
				    	Thread.sleep(1000);
				    }
				    System.out.println("Found MAT "+ mat.getVariable("MAT") +"....");
					mat.eval("class_1 = "+model.getUser_1_ID()+";",null,null);
					mat.eval("class_2 = "+model.getUser_2_ID()+";",null,null);
					mat.eval("class_3 = "+model.getUser_3_ID()+";",null,null);
					mat.eval("class_4 = "+model.getUser_4_ID()+";",null,null);
				
					String ML_features_Database_train = Matlab_Path + "/MATLAB_TRAIN_DATA/" + "Model_" + model.getModel_ID() + ".mat";			
					mat.eval("path = '"+ML_features_Database_train+"'"+";",null,null);
					String Trained_Model_File = Matlab_Path + "/MATLAB_TRAINED_MODELS/" + "Model_" + model.getModel_ID() + ".mat";
					mat.eval("path2 = '"+Trained_Model_File+"'"+";",null,null);
					System.out.println("Training "+"Model " + model.getModel_ID() +".........");
					long startTime = System.currentTimeMillis();
					mat.eval("run('" + Matlab_Path_train + "/RUN_ESS5.m')",null,null);		
					long endTime = System.currentTimeMillis();
					System.out.println("Model " + model.getModel_ID() + " Finished Training: Time = " +(endTime - startTime)/1000 + " sec and Accuracy = " + mat.getVariable("accuracy")+ "%");
					MATEngines.Release_MATEngine(mat.getVariable("MAT"));
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}			
		}).start();
	}

	public static void Extract_Features(Scanner in, String Matlab_Path_train, String Matlab_Path)
	{
		try
		{
			int num_Users = 1;
			int num_Models = 1;
			System.out.println("Searching MATEng....");
		    MatlabEngine matEng  = MATEngines.Get_Free_MATEngi();
		    while(matEng == null)
		    {
		    	matEng  = MATEngines.Get_Free_MATEngi();
		    	Thread.sleep(1000);
		    }
		    for(int i = 0; i < 0; i++ )
		    {
		    	in.nextLine();
		    }
			for(int k = 0; k < 33; k++)
			{
				String Line = in.nextLine();
				System.out.println(Line.split(" ")[1]);

				String ML_features_Database2 = Matlab_Path + "/MATLAB_TRAIN_DATA/" + "MODEL_"+ num_Models + "_test.mat";		
				matEng.eval("path = '"+ML_features_Database2+"';",null,null);
				matEng.eval("path2 = '"+ML_features_Database2+"';",null,null);
				matEng.eval("user_ID = "+Line.split(" ")[0]+";",null,null);
				for(int i = 1; i < 16; i++)
				{
					File image = new File("C:/Users/Administrator/Pictures/faces94/faces94/female/"+Line.split(" ")[1]+"/"+Line.split(" ")[1]+"."+i+".jpg");
					matEng.eval("image_Path = '"+ image.getPath()+"';",null,null);
					matEng.eval("I1 = imread(image_Path);",null,null);
					matEng.eval("run('" + Matlab_Path_train + "/Extract_Features_PCA_SURF.m')",null,null);
				}
				num_Users++;
				if(num_Users == 5)
				{
					num_Users = 1;
					num_Models++;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static double[] Predict_User(Models model,String Image_Name)
	{
		try
		{
			System.out.println("Searching MATEng....");
		    MatlabEngine mat  = MATEngines.Get_Free_MATEngi();
		    while(mat == null)
		    {
		    	mat  = MATEngines.Get_Free_MATEngi();
		    	Thread.sleep(1000);
		    }
		    System.out.println("Found MAT "+ mat.getVariable("MAT") +"....");
			File image = new File("C:/Users/Administrator/Pictures/PREDICTION TESTING/"+Image_Name+".jpg");
			mat.eval("image_Path = '"+ image.getPath()+"';",null,null);
			mat.eval("I1 = imread(image_Path);",null,null);


			String Trained_Model_File = Matlab_Path + "/MATLAB_TRAINED_MODELS/" + "Model_" + model.getModel_ID() + ".mat";
			mat.eval("path = '"+Trained_Model_File+"'"+";",null,null);
			
			System.out.println("Predicting Model " + model.getModel_ID() +".........");
			
			
			mat.eval("run('" + Matlab_Path_train + "/Predict_User10.m')",null,null);		
			
			

			double[] array = mat.getVariable("trained");
			MATEngines.Release_MATEngine(mat.getVariable("MAT"));
			return array;

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}

	}
}
