package nitmas.groupB.onestopsolution.MAIN.Task

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import nitmas.groupB.onestopsolution.MAIN.MySingleton
import nitmas.groupB.onestopsolution.R
import org.json.JSONObject


class test : Fragment(), handleclickio {

    lateinit var recyclerView: RecyclerView
    lateinit var testlist:ArrayList<testDB>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_test, container, false)

        recyclerView=rootView.findViewById(R.id.testrv)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        recyclerView.setHasFixedSize(true)

        testlist= arrayListOf<testDB>()


        val bundle = arguments
        val valu = bundle!!.getString("my_channel")
        Toast.makeText(activity, "test $valu", Toast.LENGTH_SHORT).show()


        fetchcurrenttestdata(valu.toString())

        return rootView
    }

    private fun fetchcurrenttestdata(ph: String) {
        //pff.visibility=View.VISIBLE
        val URL = "http://3.14.149.246/New%20folder%20(4)/currenttestchannel.php"
        val jsonObject = JSONObject()
        jsonObject.put("phuser",ph)
        //val jsonArray= jsonObject.toJSONArray(jsonObject.names())

        var moStringRequest =
            object : JsonObjectRequest(
                Method.POST,
                URL,jsonObject
                , Response.Listener { response ->
                    val userjsonarray=response.getJSONArray("userTestChannel")
                   // Toast.makeText(context,"${response}",Toast.LENGTH_LONG).show()
                    for(i in 0 until userjsonarray.length()) {
                        val newsJsonObject = userjsonarray.getJSONObject(i)
                        val news = testDB(
                            newsJsonObject.getString("subjecttest_name"),
                            newsJsonObject.getString("subjecttest_teacher"),
                            newsJsonObject.getString("Reference user")
                        )
                        testlist.add(news)
                    }

                    recyclerView.adapter=testAdapter(testlist,this)
                   // pff.visibility=View.GONE


                }, Response.ErrorListener { error ->
                   // pff.visibility=View.GONE
                    Log.d(ContentValues.TAG, "testfragment",error)
                    Toast.makeText(context,"CouldnotLoad",Toast.LENGTH_SHORT).show()


                }

            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = java.util.HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

        this?.let { context?.let { it1 -> MySingleton.getInstance(it1).addToRequestQueue(moStringRequest) } }
    }
    private fun replacefragment(fragment: Any) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment as Fragment)
        fragmentTransaction.commit()

    }

    override fun onItemClickedd(item: testDB) {

        Toast.makeText(context," iteclicked", Toast.LENGTH_SHORT).show()
        replacefragment(Testplace())
    }

}
