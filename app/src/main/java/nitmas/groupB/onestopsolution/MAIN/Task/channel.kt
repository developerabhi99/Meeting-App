package nitmas.groupB.onestopsolution.MAIN.Task

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject


class channel : Fragment(), handleclicki {

   lateinit var channelrv:RecyclerView
   lateinit var cchanne:Button
   lateinit var channellist:ArrayList<channelDB>
   lateinit var pff:ProgressBar
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        channelButton.setOnClickListener {
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview= inflater.inflate(R.layout.fragment_channel, container, false)
       pff=rootview.findViewById(R.id.pf)

       cchanne = rootview.findViewById(R.id.cchannel)




        channelrv=rootview.findViewById(R.id.channelrv)

        channelrv.layoutManager=LinearLayoutManager(activity)
         channelrv.setHasFixedSize(true)

        channellist= arrayListOf<channelDB>()

        val bundle = arguments
        val valu = bundle!!.getString("my_channel")
       Toast.makeText(activity, "bundle $valu", Toast.LENGTH_SHORT).show()
        //fetchData()
        cchanne.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("my_keychannel", valu.toString())
            val fg = createchannel()
            fg.setArguments(bundle)
            if (savedInstanceState == null) {
                parentFragmentManager.beginTransaction().add(R.id.frame,fg).commit()
            }
        }
        fetchdatacurrent(valu.toString())


        return rootview





    }

    private fun fetchdatacurrent(ph: String)  {
        //pff.visibility=View.VISIBLE
        val URL = "http://3.14.149.246/New%20folder%20(4)/currentuserchannel.php"
        val jsonObject = JSONObject()
        jsonObject.put("phuser",ph)
       //val jsonArray= jsonObject.toJSONArray(jsonObject.names())

        var mStringRequest =
            object : JsonObjectRequest(
                Method.POST,
                URL,jsonObject
                , Response.Listener { response ->
                    val userjsonarray=response.getJSONArray("userData")
                    // Toast.makeText(context,"${response}",Toast.LENGTH_LONG).show()
                    for(i in 0 until userjsonarray.length()) {
                        val newsJsonObject = userjsonarray.getJSONObject(i)
                        val news = channelDB(
                            newsJsonObject.getString("subject_name"),
                            newsJsonObject.getString("subject_teacher"),
                            newsJsonObject.getString("semester")
                        )
                        channellist.add(news)
                    }

                    channelrv.adapter=channelAdapter(channellist,this)
                    pff.visibility=View.GONE


                }, Response.ErrorListener { error ->
                    pff.visibility=View.GONE
                    Log.d(ContentValues.TAG, "load: abhijit",error)
                    Toast.makeText(context,"CouldnotLoad",Toast.LENGTH_SHORT).show()


                }

            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = java.util.HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

        this?.let { context?.let { it1 -> MySingleton.getInstance(it1).addToRequestQueue(mStringRequest) } }
    }

    private fun fetchData() {
        //volly library
        pff.visibility=View.VISIBLE
        val url =  "http://3.14.149.246/New%20folder%20(4)/fetchallchannel.php"
        //making a request
        val jsonArray = object: JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
               // val channelJsonArray = it.getJSONArray("")
//                val newsArray = ArrayList<newsdata>()
                for(i in 0 until it.length()) {
                    val newsJsonObject = it.getJSONObject(i)
                    val news = channelDB(
                        newsJsonObject.getString("subject_name"),
                        newsJsonObject.getString("subject_teacher"),
                        newsJsonObject.getString("semester")
                    )
                    channellist.add(news)
                }

                channelrv.adapter=channelAdapter(channellist,this)
                pff.visibility=View.GONE
                //channellist.add(channelDB("a","b","c"))
                // userlist.addAll(newsArray)
            },
            Response.ErrorListener {
                pff.visibility=View.GONE
                Log.d(ContentValues.TAG, "load: abhijit",it)
                Toast.makeText(context,"CouldnotLoad",Toast.LENGTH_SHORT).show()
            }

        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        context?.let { MySingleton.getInstance(it).addToRequestQueue(jsonArray) }
    }



    private fun replacefragment(fragment: Any) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment as Fragment)
        fragmentTransaction.commit()

    }

    override fun onItemClicked(item: channelDB) {

        Toast.makeText(context," iteclicked",Toast.LENGTH_SHORT).show()

       replacefragment(meeting())




    }






}
