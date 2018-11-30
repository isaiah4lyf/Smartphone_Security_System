import java.util.concurrent.ExecutionException;

import com.mathworks.engine.MatlabEngine;

public class MATEngines_Management {
	private MatlabEngine matEng1;
	private MatlabEngine matEng2;
	private MatlabEngine matEng3;
	private MatlabEngine matEng4;

	private boolean Mat1_In_Use;
	private boolean Mat2_In_Use;
	private boolean Mat3_In_Use;
	private boolean Mat4_In_Use;
	public MATEngines_Management() {};
	public void Start_MATEngines()
	{
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			Mat1_In_Use = true;
            			matEng1 = MatlabEngine.startMatlab();
            			System.out.println("Matlab Engine " + 1 + " initialized....");
            			Mat1_In_Use = false;
            		} catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                catch (Exception ex)
                {
                }
            }
        }).start();
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			Mat2_In_Use = true;
            			matEng2 = MatlabEngine.startMatlab();
            			System.out.println("Matlab Engine " + 2 + " initialized....");
            			Mat2_In_Use = false;
            		} catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                catch (Exception ex)
                {
                }
            }
        }).start();
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			Mat3_In_Use = true;
            			matEng3 = MatlabEngine.startMatlab();
            			System.out.println("Matlab Engine " + 3 + " initialized....");
            			Mat3_In_Use = false;
            		} catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                catch (Exception ex)
                {
                }
            }
        }).start();
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			Mat4_In_Use = true;
            			matEng4 = MatlabEngine.startMatlab();
            			System.out.println("Matlab Engine " + 4 + " initialized....");
            			Mat4_In_Use = false;
            		} catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }
                catch (Exception ex)
                {
                }
            }
        }).start();
	}
	public MatlabEngine Get_Free_MATEngi()
	{
		if(Mat1_In_Use == false)
		{
			Mat1_In_Use = true;
			try {
				matEng1.eval("MAT = 1;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng1;
		}
		else if(Mat2_In_Use == false)
		{
			Mat2_In_Use = true;
			try {
				matEng2.eval("MAT = 2;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng2;
		}
		else if(Mat3_In_Use == false)
		{
			Mat3_In_Use = true;
			try {
				matEng3.eval("MAT = 3;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng3;
		}
		else if(Mat4_In_Use == false)
		{
			Mat4_In_Use = true;
			try {
				matEng4.eval("MAT = 4;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng4;
		}
		else
		{
			return null;
		}
	}
	public void Release_MATEngine(double MATEngine)
	{
		if(MATEngine == 1.0)
		{
			Mat1_In_Use = false;
		}
		else if(MATEngine == 2.0)
		{
			Mat2_In_Use = false;
		}
		else if(MATEngine == 3.0)
		{
			Mat3_In_Use = false;
		}
		else if(MATEngine == 4.0)
		{
			Mat4_In_Use = false;
		}
	}
}
