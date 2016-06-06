package com.freedcam.apis.camera1.camera.parameters.manual;

import android.hardware.Camera;

import com.freedcam.apis.KEYS;
import com.freedcam.apis.camera1.camera.parameters.ParametersHandler;
import com.freedcam.utils.Logger;

/**
 * Created by GeorgeKiarie on 02/04/2016.
 */
public class FocusManualHuawei extends BaseFocusManual {
    public FocusManualHuawei(Camera.Parameters parameters, String maxValue, String MinValue, String manualFocusModeString, ParametersHandler parametersHandler, float step, int manualFocusType) {
        super(parameters, KEYS.HW_MANUAL_FOCUS_STEP_VALUE, KEYS.HW_VCM_END_VALUE, KEYS.HW_VCM_START_VALUE, KEYS.KEY_FOCUS_MODE_MANUAL, parametersHandler, (float) 10, 0);
    }

    public FocusManualHuawei(Camera.Parameters parameters, String value, int min, int max, String manualFocusModeString, ParametersHandler parametersHandler, float step, int manualFocusType) {
        super(parameters, value, min, max, manualFocusModeString, parametersHandler, step, manualFocusType);
    }

    @Override
    public void SetValue(final int valueToSet)
    {
        currentInt = valueToSet;

        if (valueToSet == 0)
        {
            parametersHandler.FocusMode.SetValue(KEYS.AUTO, true);
            parameters.set(KEYS.HW_HWCAMERA_FLAG,KEYS.ON);
            parameters.set(KEYS.HW_MANUAL_FOCUS_MODE,KEYS.OFF);
        }
        else
        {
            if ((!manualFocusModeString.equals("") || manualFocusModeString == null)&& !parametersHandler.FocusMode.GetValue().equals(manualFocusModeString)) //do not set "manual" to "manual"
                parametersHandler.FocusMode.SetValue(manualFocusModeString, false);
            parameters.set(KEYS.HW_HWCAMERA_FLAG,KEYS.ON);
            parameters.set(KEYS.HW_MANUAL_FOCUS_MODE,KEYS.ON);
            parameters.set(key_value, stringvalues[currentInt]);
            Logger.d(TAG, "Set " + key_value + " to : " + stringvalues[currentInt]);
            parametersHandler.SetParametersToCamera(parameters);
        }
    }
}