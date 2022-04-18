package nitmas.groupB.onestopsolution.MAIN.Task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import nitmas.groupB.onestopsolution.MAIN.MainActivity
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject
import java.util.*


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class createchannel : Fragment() {

lateinit var save:Button
lateinit var cname:EditText
lateinit var cteacher:EditText
lateinit var csem:EditText
lateinit var name: String
lateinit var teacher: String
lateinit var sem: String
lateinit var currentuser: String
lateinit var cid: String
 var number:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootview= inflater.inflate(R.layout.fragment_createchannel, container, false)

       save=rootview.findViewById(R.id.ccsave)
        cname=rootview.findViewById(R.id.ccname)
        cteacher=rootview.findViewById(R.id.ccteacher)
        csem=rootview.findViewById(R.id.ccsem)

         name= cname.getText().toString()
         teacher=cteacher.getText().toString()
        sem=csem.getText().toString()
       // number= Random().nextInt(99999)
        val bundle = arguments
         currentuser = bundle!!.getString("my_keychannel").toString()
       // Toast.makeText(activity, "bundlecreate $currentuser", Toast.LENGTH_SHORT).show()
//
      // cid=cname.getText().toString().substring(5);
         //cid="984"

        save.setOnClickListener {
           //Toast.makeText(activity, "bundlecreate ${currentuser.toString()}", Toast.LENGTH_SHORT).show()
           Toast.makeText(context,"${cname.getText().toString().substring(3)+Random().nextInt(999999)}",Toast.LENGTH_LONG).show()
            postchannel(cname.getText().toString().substring(3)+Random().nextInt(999999),
                cname.text.toString(),
                cteacher.text.toString(),
                csem.text.toString(),currentuser.toString())

        }

        return rootview

    }

    private fun postchannel(
        cid: String,
        name: String,
        teacher: String,
        sem: String,
        currentuser: String
    ) {
            val URL = "http://3.14.149.246/New%20folder%20(4)/insertchannel.php"
            //String Request initialized
            var mStringRequest =
                object : StringRequest(Method.POST, URL, Response.Listener { response ->
                    Toast.makeText(context,"Inserted Successfully",Toast.LENGTH_SHORT).show()
                //implement this fragment to channel fragment


                }, Response.ErrorListener { error ->
                    Toast.makeText(context,"not inserted",Toast.LENGTH_SHORT).show()
                    Log.d("This is the error", "Error :" + error.toString())

                }) {
                    override fun getBodyContentType(): String {
                        return "application/json"
                    }

                    @Throws(AuthFailureError::class)
                    override fun getBody(): ByteArray {
//                        jsonBody.put("ch_id", cid)
//                        jsonBody.put("subject_name", name)
//                        jsonBody.put("subject_teacher",teacher)
//                        jsonBody.put("semester",sem)
//                        jsonBody.put("Reference_ph",currentuser)


                        val params2 = HashMap<String, String>()
                        params2.put("ch_id",cid)
                        params2.put("subject_name", name)
                        params2.put("subject_teacher",teacher)
                        params2.put("semester",sem)
                        params2.put("Reference_ph",currentuser)

                        return JSONObject(params2 as Map<*, *>).toString().toByteArray()
                    }

                }
            context?.let { MySingleton.getInstance(it).addToRequestQueue(mStringRequest) }
    }


}


