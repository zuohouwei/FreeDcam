package com.freedcam.apis.camera2.camera.parameters.modes;

import android.annotation.TargetApi;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;

import com.freedcam.apis.camera2.camera.CameraHolder;

/**
 * Created by Ingo on 01.05.2015.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FocusModeApi2 extends BaseModeApi2
{
    public enum FocusModes
    {
        off,
        auto,
        macro,
        continouse_movie,
        continouse_picture,
        edof,
    }

    public FocusModeApi2(CameraHolder cameraHolder) {
        super(cameraHolder);
    }

    @Override
    public boolean IsSupported()
    {
        return cameraHolder.characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES) != null;
    }

    @Override
    public void SetValue(String valueToSet, boolean setToCamera)
    {
        if (valueToSet.contains("unknown Focus"))
            return;
        FocusModes sceneModes = Enum.valueOf(FocusModes.class, valueToSet);
        cameraHolder.SetParameterRepeating(CaptureRequest.CONTROL_AF_MODE, sceneModes.ordinal());
        BackgroundValueHasChanged(valueToSet);
        //cameraHolder.mPreviewRequestBuilder.build();
    }

    @Override
    public String GetValue()
    {

        int i = cameraHolder.get(CaptureRequest.CONTROL_AF_MODE);
        FocusModes sceneModes = FocusModes.values()[i];
        return sceneModes.toString();
    }

    @Override
    public String[] GetValues()
    {
        int[] values = cameraHolder.characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        String[] retvals = new String[values.length];
        for (int i = 0; i < values.length; i++)
        {
            try {
                FocusModes sceneModes = FocusModes.values()[values[i]];
                retvals[i] = sceneModes.toString();
            }
            catch (Exception ex)
            {
                retvals[i] = "unknown Focus" + values[i];
            }

        }
        return retvals;
    }
}
