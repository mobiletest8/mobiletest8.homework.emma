# mobiletest8.homework.week2.emma
*     [作业1: Android各个工具的使用](#作业1)
*     [作业2: adb各个命令](#作业2)
*     [作业3：monkey三种不同策略的脚本，并阐述策略](#作业3)
*     [作业4：安装ideviceinstaller，下载一个open source的project，在simulator上运行](#作业4)



<h2 id="作业1">作业1</h2>
###<h3 id="ddms">ddms.bat</h3>
![alt text][img01]

  [img01]:https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week2/src/common/images/ddms.png
  
  
<h2 id="作业3">作业3</h2>
<h3 id="monkeycommand1">Monkey命令一</h3>

      adb shell monkey -p com.xxx.xxx -p com.android.camera 5000
***策略阐述: ***
      
      当应用依赖与第三方应用（比如系统相机、地图之类),在测试应用的基础上新增依赖的应 用的关联测试
      
<h3 id="monkeycommand2">Monkey命令二</h3>

      adb -s NX511J shell monkey -p com.android.mms -p com.xxx.xxx --pct-syskeys 10 —-pct-motion 30 —-pct-touch 10 —-pct-trackball 0 —-pct-appswitch 20 —ignore-crashes —ignore-timeouts --monitor-native-crashes --ignore-security-exceptions  --throttle 400 -s 123 -v -v -v 10000 >YY_MM_DD_monkeytest.txt
***策略阐述: ***
      
      忽略异常的测试，可以在一次执行中发现多个问题
      
      
<h3 id="monkeycommand3">Monkey命令三</h3>

      adb shell monkey -p com.xxx.xxx --throttle 300
***策略阐述: ***
      
      压力测试，缩短monkey测试中事件与事件之间的延迟时间，缩小throttle值，验证在快速的事件响应的过程中，程序是否能正常运行
      
      
 <h2 id="作业4">作业4</h2>
 ###<h3 id="simulator">open source project on simulator</h3>
![alt text][img02]

  [img02]:https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week2/src/common/images/open_source_project_simulator.png
  
   ###<h3 id="device">open source project on device</h3>
![alt text][img03]

  [img03]:https://github.com/emmahuang/mobiletest8.homework.emma/blob/master/week2/src/common/images/open_source_project_%E7%9C%9F%E6%9C%BA.png
  
      


