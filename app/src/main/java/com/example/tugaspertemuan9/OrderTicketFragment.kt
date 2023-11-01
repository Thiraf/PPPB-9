package com.example.tugaspertemuan9

// Mengimpor kelas-kelas yang diperlukan dari pustaka Android.
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tugaspertemuan9.databinding.FragmentOrderTicketBinding
import com.example.tugaspertemuan9.databinding.FragmentTicketBinding


// Deklarasi kelas OrderTicketFragment yang merupakan subkelas dari Fragment.
class OrderTicketFragment : Fragment() {
    // Deklarasi properti privat untuk menyimpan referensi binding tata letak fragment.
    private lateinit var binding: FragmentOrderTicketBinding
    // Metode onCreateView() dipanggil saat tata letak fragment pertama kali dibuat.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menggunakan FragmentOrderTicketBinding untuk meng-inflate tata letak fragment dari XML.
        binding = FragmentOrderTicketBinding.inflate(inflater, container, false )
        return binding.root
    }

    // Metode onViewCreated() dipanggil setelah tata letak fragment dan tampilan terkait sudah dibuat.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            // Menangani klik pada tombol untuk memilih jenis tiket.
            orderTicketFragmentEdit.setOnClickListener{
                // Menggunakan Safe Args untuk melakukan navigasi ke fragment GetTicketFragment.
                val action = OrderTicketFragmentDirections.actionOrderTicketFragmentToGetTicketFragment()
                findNavController().navigate(action)
            }

            // Menangani klik pada tombol untuk kembali ke fragment sebelumnya.
            orderTicketFragmentBtn.setOnClickListener{
                findNavController().navigateUp()
            }

            // Menerima data "jenisTiket" dari fragment sebelumnya menggunakan savedStateHandle.
            findNavController().currentBackStackEntry?.savedStateHandle?.let {
                    handle ->
                handle.getLiveData<String>("jenisTiket").observe(viewLifecycleOwner){
                        res->
                    // Menampilkan jenis tiket yang dipilih ke dalam EditText.
                    orderTicketFragmentEdit.setText(res)
                }
            }
        }
    }
}