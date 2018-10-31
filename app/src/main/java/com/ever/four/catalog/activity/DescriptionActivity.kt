package com.ever.four.catalog.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import com.ever.four.catalog.R
import com.ever.four.catalog.util.ImageRetriever
import org.apache.commons.lang3.StringUtils

import kotlinx.android.synthetic.main.activity_description.*

//https://github.com/chrisbanes/cheesesquare
class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var extras = intent.extras

        setupTitle(extras.get("title") as String)

        setupDescription(extras.get("description") as String)

        setupMainImage(extras.get("image") as String)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupTitle(title: String) {
        if (StringUtils.isNoneEmpty(title)) {
            supportActionBar?.title = title
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    private fun setupDescription(descrip: String) {
        if (StringUtils.isNoneEmpty(descrip)) {
            description.setText(descrip)
        } else {
            description.setText(getString(R.string.no_description))
            description.setGravity(Gravity.CENTER)
        }
    }

    private fun setupMainImage(imageUrl: String){
        var imageRetriever = ImageRetriever(imageUrl, image_description)
        imageRetriever.addImage()
    }
}
