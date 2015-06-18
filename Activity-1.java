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
//�n���o�P�����W�����Ʀr,�����oSensorManager���ѷ�,�z�L�I�sgetSystemService,���wSENSOR_SERVICE,���o�P������Manager������
		findViews ();
	}
	
	private void findViews () {
		tvMag=(TextView)findViewById(R.id.tvMsg);//���oTextView������,���F�e�{������
	}

	SensorEventListener listener=new SensorEventListener () {
		public void onSensorChanger (SensorEvent event) {
//�p�Gsenser���ȽT�꦳���ܪ���,������onSensorChanger�|�Q�I�s,�|��Ĳ�o���ƥ󪺪���ǦܤU��
			Sensor sensor=event.sensor;//���osencer������
	
			StringBuilder sensorInfo=new StringBuilder ();

			sensorInfo.append ("sensor name:"+sensor.getName ()+"\n");//���osencer���W�r

			sensorInfo.append ("sensor type:"+sensor.getType ()+"\n");//���osencer�������bAndroid�C������

			sensorInfo.append ("used power:"+sensor.getPower ()+"mA\n");//���osencer���ӹq�q

			sensorInfo.append ("valuse: \n");

			float [] values=event.values;//���o�P������~�ɪ��ܤ�

			for (int i=0;i<values.length;i++) {
				sensorInfo.append("-values["+i+"]="+values[i]+"\n");
			}
			
			tvMsg.setText (sensorInfo);
		}

		public void onAccuracyChanged (Sensor sensor,int accuracy) {
			//��P��������ǫק��ܷ|�I�s����k
		}
	};

	@Override

	protected void onResume () {
		super.onResume ();

		sensorMgr.registerListener(listener,//senser manager ���U�F Listener, Listener ��ťsenser���ȬO�_������
					   sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//�����n��ť���P����(ACCELEROMETER�[�t�׷P����)
					   SensorManager.SENSOR_DELAY_UI);������W�v(����/s)
	}

	@Override
	protected void onPause () {
		super.onPause ();
	
		sensorMgr.unregisterListener (Listener);
	}
}
			

