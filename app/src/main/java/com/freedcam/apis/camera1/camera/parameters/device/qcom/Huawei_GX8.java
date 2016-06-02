package com.freedcam.apis.camera1.camera.parameters.device.qcom;

import android.hardware.Camera;
import android.os.Handler;

import com.freedcam.apis.basecamera.camera.parameters.manual.AbstractManualParameter;
import com.freedcam.apis.basecamera.camera.parameters.modes.MatrixChooserParameter;
import com.freedcam.apis.camera1.camera.CameraHolderApi1;
import com.freedcam.apis.camera1.camera.CameraUiWrapper;
import com.freedcam.apis.camera1.camera.parameters.CamParametersHandler;
import com.freedcam.apis.camera1.camera.parameters.KEYS;
import com.freedcam.apis.camera1.camera.parameters.device.BaseQcomDevice;
import com.freedcam.apis.camera1.camera.parameters.manual.FocusManualHuawei;
import com.troop.androiddng.DngProfile;

/**
 * Created by troop on 01.06.2016.
 */
public class Huawei_GX8 extends BaseQcomDevice {
    public Huawei_GX8(Handler uihandler, Camera.Parameters parameters, CameraUiWrapper cameraUiWrapper) {
        super(uihandler, parameters, cameraUiWrapper);
    }

    @Override
    public AbstractManualParameter getManualFocusParameter() {
        return new FocusManualHuawei(parameters, "hw-vcm-end-value","hw-vcm-start-value", KEYS.KEY_FOCUS_MODE_MANUAL,camParametersHandler,10,0);
    }

    public boolean IsDngSupported() {
        return true;
    }

    @Override
    public AbstractManualParameter getCCTParameter() {
        return null;
    }

    @Override
    public DngProfile getDngProfile(int filesize)
    {
        switch (filesize)
        {
            case 16424960:
                return new DngProfile(64, 4208, 3120, DngProfile.Mipi, DngProfile.RGGB, DngProfile.ROWSIZE,matrixChooserParameter.GetCustomMatrix(MatrixChooserParameter.NEXUS6));
        }
        return null;
    }
}
