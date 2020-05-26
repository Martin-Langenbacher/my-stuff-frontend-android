package de.telekom.sea.mystuff.frontend.android;

import android.widget.Toast;

import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import lombok.Getter;

public class MyStuffContext {

    private MyStuffApplication app;

    @Getter
    private ApiFactory apiFactory;

    public MyStuffContext init(MyStuffApplication myStuffApplication){

        this.app = myStuffApplication;
        this.apiFactory = new ApiFactory();
        return this;
    }

    public String getString(int resourceId){
        return app.getString(resourceId);
    }

    public void sendInfoMessage(int resId){
        Toast.makeText(this.app.getApplicationContext(), getString(resId), Toast.LENGTH_LONG).show();
    }

    public void sendInfoMessage(String msg){
        Toast.makeText(this.app.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


}
