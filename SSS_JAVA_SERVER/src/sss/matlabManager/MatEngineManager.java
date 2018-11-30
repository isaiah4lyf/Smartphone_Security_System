package sss.matlabManager;
import java.awt.TextArea;
import java.util.concurrent.ExecutionException;

import com.mathworks.engine.MatlabEngine;
public class MatEngineManager {
	private MatlabEngine matEng1;
	private MatlabEngine matEng2;
	private MatlabEngine matEng3;
	private MatlabEngine matEng4;

	private boolean Mat1InUse;
	private boolean Mat2InUse;
	private boolean Mat3InUse;
	private boolean Mat4InUse;
	
	private TextArea consoleLike;
	public MatEngineManager(TextArea consoleLike) {this.consoleLike = consoleLike;};
	public void StartMATEngines()
	{
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
            		try 
            		{
            			Mat1InUse = true;
            			matEng1 = MatlabEngine.startMatlab();
            			consoleLike.append("Matlab Engine " + 1 + " initialized....\n");
            			Mat1InUse = false;
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
            			Mat2InUse = true;
            			matEng2 = MatlabEngine.startMatlab();
            			consoleLike.append("Matlab Engine " + 2 + " initialized....\n");
            			Mat2InUse = false;
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
            			Mat3InUse = true;
            			matEng3 = MatlabEngine.startMatlab();
            			consoleLike.append("Matlab Engine " + 3 + " initialized....\n");
            			Mat3InUse = false;
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
            			Mat4InUse = true;
            			matEng4 = MatlabEngine.startMatlab();
            			consoleLike.append("Matlab Engine " + 4 + " initialized....\n");
            			Mat4InUse = false;
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
	public MatlabEngine GetFreeMATEngi()
	{
		if(Mat1InUse == false)
		{
			Mat1InUse = true;
			try {
				matEng1.eval("MAT = 1;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng1;
		}
		else if(Mat2InUse == false)
		{
			Mat2InUse = true;
			try {
				matEng2.eval("MAT = 2;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng2;
		}
		else if(Mat3InUse == false)
		{
			Mat3InUse = true;
			try {
				matEng3.eval("MAT = 3;", null, null);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return matEng3;
		}
		else if(Mat4InUse == false)
		{
			Mat4InUse = true;
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
	public void ReleaseMATEngine(double MATEngine)
	{
		if(MATEngine == 1.0)
		{
			Mat1InUse = false;
		}
		else if(MATEngine == 2.0)
		{
			Mat2InUse = false;
		}
		else if(MATEngine == 3.0)
		{
			Mat3InUse = false;
		}
		else if(MATEngine == 4.0)
		{
			Mat4InUse = false;
		}
	}
	public boolean isMat1InUse() {
		return Mat1InUse;
	}
	public void setMat1InUse(boolean mat1InUse) {
		Mat1InUse = mat1InUse;
	}
	public boolean isMat2InUse() {
		return Mat2InUse;
	}
	public void setMat2InUse(boolean mat2InUse) {
		Mat2InUse = mat2InUse;
	}
	public boolean isMat3InUse() {
		return Mat3InUse;
	}
	public void setMat3InUse(boolean mat3InUse) {
		Mat3InUse = mat3InUse;
	}
	public boolean isMat4InUse() {
		return Mat4InUse;
	}
	public void setMat4InUse(boolean mat4InUse) {
		Mat4InUse = mat4InUse;
	}
}
