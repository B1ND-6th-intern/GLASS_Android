package kr.hs.dgsw.smartschool.glass_android.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kr.hs.dgsw.smartschool.glass_android.viewmodel.activity.MainViewModel
import kr.hs.dgsw.smartschool.glass_android.R
import kr.hs.dgsw.smartschool.glass_android.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
        var permissionWorld = -1
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setBottomNav()

        if ((checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
            (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            Log.d("PERMISSION", "onCreate: granted")
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE)
        }

        with(mainViewModel) {
            getPermission()

            permissionVal.observe(this@MainActivity, {
                permissionWorld = permissionVal.value!!
                when(permissionVal.value) {
                    0 -> {
                        binding.btnPosting.visibility = View.VISIBLE
                    }
                    1 -> {
                        binding.btnPosting.visibility = View.GONE
                    }
                    2 -> {
                        binding.btnPosting.visibility = View.VISIBLE
                    }
                }
            })
            
            message.observe(this@MainActivity, {
                Toast.makeText(applicationContext, "${message.value}", Toast.LENGTH_SHORT).show()
            })

            onPostEvent.observe(this@MainActivity, {
                 findNavController(R.id.nav_host_fragment).apply{
//                     navigateUp()
                     navigate(R.id.action_main_home_to_postFragment)
                 }
            })

            onSettingEvent.observe(this@MainActivity, {
                findNavController(R.id.nav_host_fragment).apply {
                    navigate(R.id.action_main_profile_to_settingFragment)
                }
            })
        }

        binding.bnvMain.itemIconTintList = null
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {  // 1
                if (grantResults.isEmpty()) {  // 2
                    throw RuntimeException("Empty permission result")
                }
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("RequestPermissions", "onRequestPermissionsResult: granted")
                } else {
                    if (shouldShowRequestPermissionRationale(STORAGE_SERVICE)) {
                        Log.d("RequestPermissions", "User declined, but i can still ask for more")
                        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                    } else {
                        Log.d("", "User declined and i can't ask")
                        showDialogToGetPermission()
                    }
                }
            }
        }
    }

    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("권한을 설정해주세요!")
            .setMessage("GLASS를 원활하게 이용하기 위해서는 권한이 팔요합니다. " +
                    "설정으로 가서 권한을 허락해주세요!")

        builder.setPositiveButton("OK") { dialogInterface, i ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        builder.setNegativeButton("Later") { dialogInterface, i ->
            // ignore
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun setBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bnvMain
            .setupWithNavController(navController)
    }

    fun setNavVisible(isMainFragment: Boolean) {
        binding.appbarHome.visibility = if(isMainFragment) View.VISIBLE else View.GONE
        binding.bnvMain.visibility = if(isMainFragment) View.VISIBLE else View.GONE
    }

    fun setAppBarVisible(isSearchFragment: Boolean) {
        binding.appbarHome.visibility = if(isSearchFragment) View.GONE else View.VISIBLE
        binding.bnvMain.visibility = if (isSearchFragment) View.VISIBLE else View.VISIBLE
    }
}