package com.example.tugaspertemuan9

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.tugaspertemuan9.databinding.FragmentGetTicketBinding

class GetTicketFragment : Fragment() {
    // Deklarasi properti privat untuk menyimpan referensi binding tata letak fragment.
    private lateinit var binding: FragmentGetTicketBinding

    // Array yang berisi pilihan jenis tiket.
    private val jenisTiket = arrayOf(
        "First Class Ticket",
        "Business Class Ticket",
        "Economy Class Ticket"
    )
    // Variabel yang menyimpan jenis tiket yang dipilih oleh pengguna.
    var selectedJenisTiket : String = ""

    // Metode onCreateView() dipanggil saat tata letak fragment pertama kali dibuat.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menggunakan FragmentGetTicketBinding untuk meng-inflate tata letak fragment dari XML.
        binding = FragmentGetTicketBinding.inflate(inflater, container, false)

        // Inisialisasi adapter untuk spinner dengan menggunakan array jenisTiket.
        with(binding) {
            val adapterJenisTiket = ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_item,
                jenisTiket
            )

            // Mengatur layout untuk dropdown item pada spinner.
            adapterJenisTiket.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )

            // Mengatur adapter untuk spinner dengan adapter yang sudah diinisialisasi.
            getTicketFragmentSpinner.adapter = adapterJenisTiket
        }

        // Mengembalikan root view dari tata letak fragment yang sudah di-inflate.
        return binding.root
    }



    // Metode onViewCreated() dipanggil setelah tata letak fragment dan tampilan terkait sudah dibuat.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            // Menangani klik pada tombol untuk menyimpan jenis tiket yang dipilih.
            getTicketFragmentBtn.setOnClickListener {
                // Mendapatkan teks yang dipilih dari spinner.
                val selectedText = getTicketFragmentSpinner.selectedItem.toString()
                // Menyimpan jenis tiket yang dipilih ke dalam state handle dan kembali ke fragment sebelumnya.
                findNavController().apply {
                    previousBackStackEntry?.savedStateHandle?.set("jenisTiket", selectedText)
                }.navigateUp()
            }


        }
    }
}