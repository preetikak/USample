package com.preetika.usample.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.preetika.usample.R
import com.preetika.usample.databinding.ActivityMainBinding
import com.preetika.usample.model.Model
import com.preetika.usample.viewmodel.CvViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var cvViewModel: CvViewModel? = null
    private var model: Model?= null
    private var liveData: LiveData<Model>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        cvViewModel= ViewModelProviders.of(this).get(CvViewModel::class.java)
        checkPermission()
        liveData= cvViewModel!!.getNewsRepository()
        liveData?.observe(this, Observer { items ->
            //Do your operation here
            Log.e("msg", "= "+ items.data.name);
            binding.tvUsername.text= HtmlCompat.fromHtml("<u><b>"+items.data.name+"</b></u>", 0)
            tvSummaryDesc.text= items.data.summary
            tvUsername.visibility= View.VISIBLE
            tvSummary.visibility= View.VISIBLE
            tvSummaryDesc.visibility= View.VISIBLE
            tvEducationalBg.visibility= View.VISIBLE
            tvSkills.visibility= View.VISIBLE
            for (i in 0..items.data.skills.size-1) {
                val tv_skill = TextView(this@MainActivity)
                tv_skill.textSize =
                    this@MainActivity.resources.getDimension(R.dimen.dynamic_textsmall)

                tv_skill.setPadding(5,5,5,5)
                tv_skill.setTextColor(this@MainActivity.resources.getColor(R.color.black))
                val list= listOf(items.data.skills.get(i).languages)
                val languageData=  list.joinToString (prefix = "", separator = ",", limit = 5, postfix = "")
                val datatext = "<b>"+items.data.skills.get(i).type+ ": </b> "+languageData.removePrefix("[").removeSuffix("]")
                tv_skill.text = HtmlCompat.fromHtml(datatext,0)
                lvSkills.addView(tv_skill)
            }
            for(i in 0..items.data.educationBg.size-1){
                val tv_skill = TextView(this@MainActivity)
                tv_skill.textSize =
                    this@MainActivity.resources.getDimension(R.dimen.dynamic_textsmall)

                tv_skill.setPadding(5,5,5,5)
                tv_skill.setTextColor(ContextCompat.getColor(this, R.color.black))
                val skillset= "<b>"+items.data.educationBg.get(i).position+ "</b>"+
                        "<br><b> Majors in: </b>"+items.data.educationBg.get(i).major+
                        "<br><b>From: </b>"+
                        items.data.educationBg.get(i).from+
                        "\t\t\t\t" +
                        "<b>To: </b>" +
                        items.data.educationBg.get(i).to+
                        "<br><b>Institute Name:</b> "+
                        items.data.educationBg.get(i).instituteName
                tv_skill.text= HtmlCompat.fromHtml(skillset,0)
                lvEducation.addView(tv_skill)
            }
            lvEducation.visibility= View.VISIBLE
            lvSkills.visibility= View.VISIBLE
        })
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,
                    Manifest.permission.INTERNET)) {

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.INTERNET),
                    1)


            }
        } else {
            // Permission has already been granted
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted,
                } else {
                    // permission denied.
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }


}
