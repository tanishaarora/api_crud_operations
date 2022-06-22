package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

class AirplaneModeChangeReceiver extends BroadcastReceiver {

 @Override
 public void onReceive(Context context, Intent intent) {
  if (isAirplaneModeOn(context.getApplicationContext())) {
   Toast.makeText(context, "AirPlane mode is on", Toast.LENGTH_SHORT).show();
 } else

 {
  Toast.makeText(context, "AirPlane mode is off", Toast.LENGTH_SHORT).show();
 }
 }

 private static boolean isAirplaneModeOn(Context context) {
  return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
 }
}



//public class Receiver extends BroadcastReceiver {

// Context context;
//
// @Override
// public void onReceive(Context context, Intent intent) {
//
//  String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//
//  if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
//   Toast.makeText(context, "Phone Is Ringing", Toast.LENGTH_LONG).show();
//  }
//
//  if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
//   Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show();
//
//  }
//  if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
//   Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show();
//
//  }
//  }
