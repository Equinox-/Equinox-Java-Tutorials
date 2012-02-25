/**
 * Copyright 2011 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JogAmp Community OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of JogAmp Community.
 */
package jogamp.common.os.android;

import android.content.*;
import android.content.pm.*;
import android.util.Log;

public class PackageInfoUtil {
   private static boolean DEBUG = false;
   
   public static final PackageInfo getPackageInfo(String packageName) {
       return getPackageInfo(StaticContext.getContext(), packageName);
   }
   
   public static final PackageInfo getPackageInfo(Context ctx, String packageName) {
       if(null != ctx) {
           try {
               final PackageInfo pi = ctx.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
               if(DEBUG) Log.d(MD.TAG, "getPackageInfo("+packageName+"): "+pi);
               return pi;
           } catch (Exception e) { if(DEBUG) { Log.d(MD.TAG, "getPackageInfo("+packageName+")", e); } }
       }
       if(DEBUG) Log.d(MD.TAG, "getPackageInfo("+packageName+"): NULL");
       return null;
   }
   
   public static final int getPackageInfoVersionCode(String packageName) {
       final PackageInfo pInfo = getPackageInfo(packageName);
       return ( null != pInfo ) ? pInfo.versionCode : -1 ;
   }   
   public static final String getPackageInfoVersionName(String packageName) {
       final PackageInfo pInfo = getPackageInfo(packageName);
       final String s = ( null != pInfo ) ? pInfo.versionName : null ;
       if(DEBUG) Log.d(MD.TAG, "getPackageInfoVersionName("+packageName+"): "+s);
       return s;
   }   
}
