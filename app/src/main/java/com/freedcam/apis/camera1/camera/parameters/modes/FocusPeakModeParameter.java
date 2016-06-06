package com.freedcam.apis.camera1.camera.parameters.modes;

import android.os.Build;

import com.freedcam.apis.camera1.camera.CameraHolder;
import com.freedcam.apis.camera1.camera.renderscript.FocusPeakProcessorAp1;
import com.freedcam.utils.StringUtils;


/**
 * Created by troop on 27.08.2015.
 */
public class FocusPeakModeParameter extends BaseModeParameter {

    private FocusPeakProcessorAp1 focusPeakProcessorAp1;
    public FocusPeakModeParameter(CameraHolder cameraHolder, FocusPeakProcessorAp1 focusPeakProcessorAp1)
    {
        super(null, cameraHolder, "", "");
        this.focusPeakProcessorAp1 = focusPeakProcessorAp1;
    }

    @Override
    public boolean IsSupported() {
        return Build.VERSION.SDK_INT >= 18;
    }

    @Override
    public void SetValue(String valueToSet, boolean setToCam) {
        if (valueToSet.equals(StringUtils.ON))
        {
            cameraHolder.GetParameterHandler().FocusMode.SetValue(cameraHolder.GetParameterHandler().FocusMode.GetValue(),true);
            focusPeakProcessorAp1.Enable(true);
        }
        else
            focusPeakProcessorAp1.Enable(false);
    }

    @Override
    public String GetValue()
    {
        if (focusPeakProcessorAp1.isEnable())
            return StringUtils.ON;
        else
            return StringUtils.OFF;
    }

    @Override
    public String[] GetValues() {
        return new String[] {StringUtils.ON, StringUtils.OFF};
    }

    @Override
    public void addEventListner(I_ModeParameterEvent eventListner)
    {
        super.addEventListner(eventListner);
    }

    @Override
    public void removeEventListner(I_ModeParameterEvent parameterEvent) {
        super.removeEventListner(parameterEvent);
    }

    @Override
    public void BackgroundValueHasChanged(String value)
    {
        if (value.equals("true"))
            super.BackgroundValueHasChanged(StringUtils.ON);
        else if (value.equals("false"))
            super.BackgroundValueHasChanged(StringUtils.OFF);
    }

    @Override
    public void BackgroundValuesHasChanged(String[] value) {

    }

    @Override
    public void BackgroundIsSupportedChanged(boolean value) {

    }

    @Override
    public void BackgroundSetIsSupportedHasChanged(boolean value) {

    }
}
