package sample.example.com.ktconf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import sample.example.com.ktconf.databinding.ActivityMainBinding
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.joinButton.setOnClickListener{
            val joinId : String = binding.editText.text.toString()
            if (joinId.isNotEmpty()){
                var options : JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder().setRoom(joinId).build()
                JitsiMeetActivity.launch(this,options)
            }
            initConf()
        }
    }

    private fun initConf(){
        lateinit var serverUrl : URL
        try {
            serverUrl = URL("https://meet.jit.si")
        }catch(ex : MalformedURLException){
            ex.stackTrace
        }
        var defaultOptions : JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder().setServerURL(serverUrl).setWelcomePageEnabled(false).build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
    }

}
