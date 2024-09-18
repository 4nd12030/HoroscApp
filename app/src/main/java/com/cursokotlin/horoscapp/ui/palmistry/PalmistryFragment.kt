package com.cursokotlin.horoscapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.cursokotlin.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.security.Permission

//Clase que recibe clases inyectadas
@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            //startCamera
        } else {
            Toast.makeText(
                requireContext(),
                "Acepta los permisos para poder disfrutar de una experiencia magica",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun checkCameraPErmisssion(): Boolean {

        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkCameraPErmisssion()) {
            //Tiene permsisos aceptados

        } else {
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        Log.i("Fragment", "$binding")
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        Log.i("Fragment", "$binding")
        return binding.root
    }


}