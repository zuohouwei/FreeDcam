package com.freedcam.apis.camera1.camera.parameters.device.qcom;

import android.hardware.Camera;
import android.os.Handler;

import com.freedcam.apis.basecamera.camera.parameters.manual.AbstractManualParameter;
import com.freedcam.apis.basecamera.camera.parameters.modes.MatrixChooserParameter;
import com.freedcam.apis.camera1.camera.CameraUiWrapper;
import com.freedcam.apis.camera1.camera.parameters.device.BaseQcomDevice;
import com.freedcam.apis.camera1.camera.parameters.manual.BaseISOManual;
import com.freedcam.apis.camera1.camera.parameters.manual.ShutterManual_ExposureTime_Micro;
import com.troop.androiddng.DngProfile;

/**
 * Created by troop on 01.06.2016.
 */
public class Lenovo_VibeP1 extends BaseQcomDevice {
    public Lenovo_VibeP1(Handler uihandler, Camera.Parameters parameters, CameraUiWrapper cameraUiWrapper) {
        super(uihandler, parameters, cameraUiWrapper);
    }

    @Override
    public AbstractManualParameter getExposureTimeParameter() {
        return new ShutterManual_ExposureTime_Micro(parameters, parametersHandler,null,"exposure-time", "max-exposure-time", "min-exposure-time");
    }

    @Override
    public AbstractManualParameter getIsoParameter() {

        return new BaseISOManual(parameters,"continuous-iso",parameters.getInt("min-iso"),parameters.getInt("max-iso"), parametersHandler,1);
    }
    public boolean IsDngSupported() {
        return true;
    }
    @Override
    public DngProfile getDngProfile(int filesize)
    {
        switch (filesize)
        {
            case 16424960:
                return new DngProfile(64, 4208, 3120, DngProfile.Mipi, DngProfile.GRBG, DngProfile.ROWSIZE,matrixChooserParameter.GetCustomMatrix(MatrixChooserParameter.NEXUS6));
        }
        return null;
    }
}
