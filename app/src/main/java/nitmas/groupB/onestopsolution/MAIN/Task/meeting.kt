package nitmas.groupB.onestopsolution.MAIN.Task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import nitmas.groupB.onestopsolution.R
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.MalformedURLException
import java.net.URL


class meeting : Fragment() {
    lateinit var meet:Button
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview= inflater.inflate(R.layout.fragment_meeting, container, false)

       try {

           val options = JitsiMeetConferenceOptions.Builder()
               .setServerURL(URL(""))
               .setAudioMuted(false)
               .setVideoMuted(false)
               .setAudioOnly(false)
               .setWelcomePageEnabled(false)
               .build()

       } catch (e: MalformedURLException) {
           e.printStackTrace()
       }
       meet=rootview.findViewById(R.id.vediocall)
       meet.setOnClickListener {

           val text:String = "onestop".toString()
           if (text.length > 0) {
               val options = JitsiMeetConferenceOptions.Builder()
                   .setRoom("https://meet.jit.si/${text}")
                   .build()
               JitsiMeetActivity.launch(context, options)
           }
       }


       return rootview
    }


}