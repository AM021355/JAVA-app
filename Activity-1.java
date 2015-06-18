package org.accelerometerEx;

import android.app.Activity;

public class AccelerometerEx extends Activity {
	private SensorManager sensorMgr;

	private TextView tvMsg;

	@Override

	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);

		setContentView (R.layout.main);

		sensorMgr=(SensorManager)getSystemService(SENSOR_SERVICE);
//要取得感應器上面的數字,先取得SensorManager的參照,透過呼叫getSystemService,指定SENSOR_SERVICE,取得感應器的Manager的物件
		findViews ();
	}
	
	private void findViews () {
		tvMag=(TextView)findViewById(R.id.tvMsg);//取得TextView的元件,為了呈現對應值
	}

	SensorEventListener listener=new SensorEventListener () {
		public void onSensorChanger (SensorEvent event) {
//如果senser的值確實有改變的話,對應的onSensorChanger會被呼叫,會把觸發的事件的物件傳至下行
			Sensor sensor=event.sensor;//取得sencer的物件
	
			StringBuilder sensorInfo=new StringBuilder ();

			sensorInfo.append ("sensor name:"+sensor.getName ()+"\n");//取得sencer的名字

			sensorInfo.append ("sensor type:"+sensor.getType ()+"\n");//取得sencer的對應在Android列表的順位

			sensorInfo.append ("used power:"+sensor.getPower ()+"mA\n");//取得sencer的耗電量

			sensorInfo.append ("valuse: \n");

			float [] values=event.values;//取得感應器對外界的變化

			for (int i=0;i<values.length;i++) {
				sensorInfo.append("-values["+i+"]="+values[i]+"\n");
			}
			
			tvMsg.setText (sensorInfo);
		}

		public void onAccuracyChanged (Sensor sensor,int accuracy) {
			//當感應器的精準度改變會呼叫此方法
		}
	};

	@Override

	protected void onResume () {
		super.onResume ();

		sensorMgr.registerListener(listener,//senser manager 註冊了 Listener, Listener 監聽senser的值是否有改變
					   sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//偵測要監聽的感應器(ACCELEROMETER加速度感應器)
					   SensorManager.SENSOR_DELAY_UI);抓取的頻率(次數/s)
	}

	@Override
	protected void onPause () {
		super.onPause ();
	
		sensorMgr.unregisterListener (Listener);
	}
}
			

