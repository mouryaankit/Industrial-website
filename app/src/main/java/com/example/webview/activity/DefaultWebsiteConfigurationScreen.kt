package com.example.webview.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webview.adapter.WebConfigAdapter
import com.example.webview.constants.Constants
import com.example.webview.customviews.ItemSpacingDecorator
import com.example.webview.databinding.ActivityDefaultWebsiteConfigurationScreenBinding
import com.example.webview.model.ComponentsModel
import com.example.webview.utils.SelectedComponentData
import com.example.webview.utils.StyleElements

class DefaultWebsiteConfigurationScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDefaultWebsiteConfigurationScreenBinding
    private lateinit var webConfigAdapter: WebConfigAdapter
    private lateinit var componentList: MutableList<ComponentsModel>
    private lateinit var selectedComponentData: SelectedComponentData
    private lateinit var styleElements: StyleElements
    private var urlLink: String? = null
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefaultWebsiteConfigurationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        componentList = ArrayList()
        selectedComponentData = SelectedComponentData(this)
        styleElements = StyleElements()
        webConfigAdapter = WebConfigAdapter(componentList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        bundle = Bundle()


        componentList.add(
            ComponentsModel(
                Constants.ConfigComponents.fontFamily,
                styleElements.getFontFamilyStyles(),
                false
            )
        )
        componentList.add(
            ComponentsModel(
                Constants.ConfigComponents.backgroundColor,
                styleElements.getBackgroundColorStyle(),
                false
            )
        )
        componentList.add(
            ComponentsModel(
                Constants.ConfigComponents.fontSize,
                styleElements.getFontSizeStyle(),
                false
            )
        )

        binding.recyclerView.adapter = webConfigAdapter
        binding.recyclerView.addItemDecoration(ItemSpacingDecorator(8))
        webConfigAdapter.notifyDataSetChanged()
        val urlLink = intent.getStringExtra("WebLink")

        binding.saveButton.setOnClickListener {
            setStyleBundle()
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("WebLink", urlLink)
            intent.putExtra("WebStyles", bundle)
            startActivity(intent)
            finish()
        }


    }

    private fun setStyleBundle() {
        componentList.forEach {
            when (it.componentName) {
                Constants.ConfigComponents.fontFamily -> {
                    val fontURL = selectedComponentData.getSelectedFontFamily(it.selectedValue)
                    bundle.putString(Constants.ConfigComponents.fontFamily + "URL", fontURL)
                    bundle.putString(
                        Constants.ConfigComponents.fontFamily + "Name",
                        it.selectedValue
                    )
                }

                Constants.ConfigComponents.backgroundColor -> {
                    val backgroundColor =
                        selectedComponentData.getSelectedBackgroundColor(it.selectedValue)
                    bundle.putInt(Constants.ConfigComponents.backgroundColor, backgroundColor)
                }

                Constants.ConfigComponents.fontSize -> {
                    val fontSize = selectedComponentData.getSelectedFontSize(it.selectedValue)
                    bundle.putStringArrayList(Constants.ConfigComponents.fontSize, fontSize)
                }
            }
        }
    }
}