package nitmas.groupB.onestopsolution.LOGIN

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_login.*
import nitmas.groupB.onestopsolution.MAIN.MainActivity
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject
import java.util.*


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener{
            //val ph=countryCode_picker.selectedCountryCode.toString()+phonenumber.text.toString()
            //startActivity(Intent(this,OtpScreen::class.java))
           checkusernumber(countryCode_picker.selectedCountryCode.toString()+phonenumber.text.toString())

        }





    }


    private fun postusernumber(
        ph: String,
    ) {
        val URL = "http://3.14.149.246/New%20folder%20(4)/insertdataalluser.php"
        //String Request initialized
        var mStringRequest =
            object : StringRequest(Method.POST, URL, Response.Listener { response ->
                Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_SHORT).show()


                val i = Intent(this,SignUp::class.java)
                val a = ph


                i.putExtra("ph", a)

                startActivity(i)


            }, Response.ErrorListener { error ->
                Toast.makeText(this,"not inserted",Toast.LENGTH_SHORT).show()
                Log.d("This is the error", "Error :" + error.toString())

            }) {
                override fun getBodyContentType(): String {
                    return "application/json"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray {

                    val params2 = HashMap<String, String>()
                    params2.put("ph",ph)
                    params2.put("fullname", "NULL")
                    params2.put("dept","NULL")
                    params2.put("regno","NULL")
                    params2.put("designation","NULL")

                    return JSONObject(params2 as Map<*, *>).toString().toByteArray()
                }

            }
        this?.let { MySingleton.getInstance(it).addToRequestQueue(mStringRequest) }
    }

    private fun checkusernumber(
        ph: String,

    ) {
        val URL = "http://3.14.149.246/New%20folder%20(4)/checkphoneno.php"
        val jsonObject = JSONObject()
        jsonObject.put("phuser",ph)

        var mStringRequest =
            object :JsonObjectRequest(
                Method.POST,
                URL,jsonObject
                , Response.Listener { response ->

                 if(response.getString("mass")=="True"){
                     val i = Intent(this,MainActivity::class.java)
                     val a = ph
                     i.putExtra("ph", a)
                     startActivity(i)
                 }else{
                     postusernumber(ph)
                 }

                //Toast.makeText(this,"${response}",Toast.LENGTH_LONG).show()


               // Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_SHORT).show()


            }, Response.ErrorListener { error ->
                Toast.makeText(this,"not inserted",Toast.LENGTH_SHORT).show()
                Log.d("This is the error", "Error :" + error.toString())


            }

            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

        this?.let { MySingleton.getInstance(it).addToRequestQueue(mStringRequest) }
    }
    override fun onBackPressed(){
        moveTaskToBack(true);
    }
}