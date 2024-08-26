package com.karamobile.bmicalculator

import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.karamobile.bmicalculator.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAge : EditText = findViewById(R.id.etAge)
        val etHeight : EditText = findViewById(R.id.etHeight)
        val etWeight : EditText = findViewById(R.id.etWeight)
        val btnCalculate : Button = findViewById(R.id.btnCalculate)
        val tvResult : TextView = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val age = etAge.text.toString().toIntOrNull() ?: 0
            val height = etHeight.text.toString().toDoubleOrNull() ?: 0.0
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0

            if (age > 0 && height > 0 && weight > 0){
                val bmi = calculateBMI(height, weight)
                val result = getBMICategory(bmi)
                tvResult.text = "Vücut Kitle İndeksi: ${String.format("%.2f", bmi)}\n$result"
            } else {
                tvResult.text = "Lütfen geçerli değerler girin."
            }
        }
    }
    private fun calculateBMI(height: Double, weight: Double): Double {
        return weight / ((height / 100) * (height / 100))
    }
    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Zayıf \n\n Vücut Kitle İndeksiniz (BMI) zayıf kategorisinde. Bu, ideal kilonuzun biraz altında olduğunuz anlamına geliyor. Sağlığınızı korumak için dengeli ve besleyici bir diyet uygulamanız önemlidir. Vücudunuza gerekli vitamin ve mineralleri alabilmek için, düzenli olarak protein, sağlıklı yağlar ve karbonhidratlar tüketmeyi ihmal etmeyin. Ayrıca, kilo almanıza yardımcı olacak direnç egzersizleri yapmayı düşünebilirsiniz. Unutmayın, sağlıklı bir yaşam için vücudunuzu dinlemek ve ona iyi bakmak çok önemlidir."
            bmi in 18.5..24.99 -> "Normal Kilolu \n\n Tebrikler! Vücut Kitle İndeksiniz (BMI) normal aralıkta, bu da sağlıklı bir kiloda olduğunuzu gösteriyor. Bu durumu korumak için, dengeli beslenmeye ve aktif bir yaşam tarzı sürdürmeye devam edin. Düzenli egzersiz yapmak, vücudunuzu güçlü ve esnek tutar. Meyve, sebze, tam tahıllar ve sağlıklı protein kaynaklarıyla beslenerek, sağlığınızı uzun vadede koruyabilirsiniz. Kendinize iyi bakmaya devam edin, çünkü sağlık en büyük zenginliktir!"
            bmi in 25.0..29.99 -> "Fazla Kilolu \n\n Vücut Kitle İndeksiniz (BMI) fazla kilolu kategorisinde. Bu, vücudunuzda biraz fazladan ağırlık taşıdığınız anlamına geliyor. Sağlıklı bir kiloya ulaşmak için, düzenli fiziksel aktivite ve dengeli beslenme önemlidir. Yüksek lif içeren gıdalar, sebzeler, meyveler ve tam tahıllar tüketerek kalori alımınızı dengeleyebilirsiniz. Ayrıca, şekerli ve işlenmiş gıdalardan kaçınarak sağlıklı bir yaşam tarzını destekleyebilirsiniz. Küçük değişikliklerle büyük farklar yaratabilir, kendinizi daha enerjik ve sağlıklı hissedebilirsiniz."
            bmi >= 30.0 -> "Obez \n\n Vücut Kitle İndeksiniz (BMI) obez kategorisinde. Bu durum, sağlığınız için bazı riskler taşıyor olabilir. Ancak, sağlıklı alışkanlıklar kazanarak bu durumu iyileştirmek mümkündür. Dengeli ve sağlıklı beslenme, düzenli egzersiz, yeterli uyku ve stres yönetimi, sağlıklı bir kiloya ulaşmanıza yardımcı olabilir. Küçük adımlarla başlayarak büyük sonuçlar elde edebilirsiniz. Bu süreçte bir uzmandan destek almak, sağlığınız için en iyi adımları atmanıza yardımcı olabilir. Unutmayın, kendinize olan inancınızla her şeyi başarabilirsiniz!"
            else -> "Bilinmiyor"
        }
    }
}

