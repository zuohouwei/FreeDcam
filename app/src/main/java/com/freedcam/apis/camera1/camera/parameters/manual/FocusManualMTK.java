package com.freedcam.apis.camera1.camera.parameters.manual;

import android.hardware.Camera;

import com.freedcam.apis.KEYS;
import com.freedcam.apis.camera1.camera.parameters.ParametersHandler;
import com.freedcam.utils.Logger;

/**
 * Created by troop on 28.03.2016.
 */
public class FocusManualMTK extends BaseFocusManual {
    public FocusManualMTK(Camera.Parameters parameters, String value, String maxValue, String MinValue, ParametersHandler parametersHandler, float step, int manualFocusType) {
        super(parameters, KEYS.FOCUS_FS_FI, KEYS.FOCUS_FS_FI_MAX, KEYS.FOCUS_FS_FI_MIN, KEYS.KEY_FOCUS_MODE_MANUAL, parametersHandler, (float) 10, 0);
        isSupported = true;
        isVisible = isSupported;
    }

    public FocusManualMTK(Camera.Parameters parameters, ParametersHandler parametersHandler) {
        //TODO check if AFENG_FI_MIN/MAX can get used
        super(parameters, KEYS.AFENG_POS, 0, 1023, KEYS.KEY_FOCUS_MODE_MANUAL, parametersHandler, (float) 10, 1);
        this.isSupported = true;
        this.isVisible = true;
        this.manualFocusModeString = KEYS.KEY_FOCUS_MODE_MANUAL;
        this.stringvalues = createStringArray(0, 1023, (float) 10);
    }

    @Override
    public void SetValue(final int valueToSet)
    {
        currentInt = valueToSet;

        if (valueToSet == 0)
        {
            parametersHandler.FocusMode.SetValue(KEYS.AUTO, true);
        }
        else
        {
            if ((!manualFocusModeString.equals("") || manualFocusModeString == null)&& !parametersHandler.FocusMode.GetValue().equals(manualFocusModeString)) //do not set "manual" to "manual"
                parametersHandler.FocusMode.SetValue(manualFocusModeString, false);

            parameters.set(key_value, stringvalues[currentInt]);
            Logger.d(TAG, "Set "+ key_value +" to : " + stringvalues[currentInt]);
            parametersHandler.SetParametersToCamera(parameters);
        }
    }
}
