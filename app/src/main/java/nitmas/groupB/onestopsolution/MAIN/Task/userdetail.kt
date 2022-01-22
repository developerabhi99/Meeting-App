package nitmas.groupB.onestopsolution.MAIN.Task

import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import nitmas.groupB.onestopsolution.LOGIN.Login
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject
import java.util.*


class userdetail : Fragment() {
lateinit var username:TextView
lateinit var logout:Button
lateinit var phonenumber:TextView
lateinit var regno:TextView
lateinit var designation:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview= inflater.inflate(R.layout.fragment_userdetail, container, false)

//        logout.setOnClickListener {
//            Toast.makeText(context,"Clicked ",Toast.LENGTH_SHORT).show()
//        }

        logout=(rootview.findViewById(R.id.logout))

       username= (rootview.findViewById(R.id.usern))

        regno=(rootview.findViewById(R.id.uniqueid))
        phonenumber=(rootview.findViewById(R.id.phoneno))
        designation=(rootview.findViewById(R.id.designation))

        val bundle = arguments
        val value = bundle!!.getString("my_key")
        //Toast.makeText(activity, value, Toast.LENGTH_SHORT).show()


        checkusernumber(value.toString())

        logout.setOnClickListener {
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }


        return rootview
    }

    private fun checkusernumber(
        ph: String,

        ) {
        val URL = "http://3.14.149.246/New%20folder%20(4)/currentuserdetail.php"
        val jsonObject = JSONObject()
        jsonObject.put("phuser",ph)

        var mStringRequest =
            object : JsonObjectRequest(
                Method.POST,
                URL,jsonObject
                , Response.Listener { response ->
                // Toast.makeText(context,"${response}",Toast.LENGTH_LONG).show()
                  username.setText(response.getString("fullname"))
                    regno.setText(response.getString("regno"))
                    phonenumber.setText(ph)
                    designation.setText(response.getString("designation"))

                }, Response.ErrorListener { error ->
                    Toast.makeText(context,"could not fetch", Toast.LENGTH_SHORT).show()
                    Log.d("This is the error", "Error :" + error.toString())


                }

            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

        this?.let { context?.let { it1 -> MySingleton.getInstance(it1).addToRequestQueue(mStringRequest) } }
    }


}