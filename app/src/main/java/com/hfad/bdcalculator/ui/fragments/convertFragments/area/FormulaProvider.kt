package com.hfad.bdcalculator.ui.fragments.convertFragments.area


class FormulaProvider {

    fun provide(from: String?, to: String?): (Double) -> Double {
        var r = { x: Double -> x }

        when (from) {
            "Acres (ac)" -> {
                if (to == "Ares (a)") r = { it * 40.468564224 }
                if (to == "Hectares (ha)") r = { it * 0.40468564224 }
                if (to == "Square centimeters (cm²)") r = { it * 40468564.224 }
                if (to == "Square feet (ft²)") r = { it * 43.560 }
                if (to == "Square inches (in²)") r = { it * 6272640.50181 }
                if (to == "Square meters (m²)") r = { it / 0.00024710538161371 }

                return r
            }

            "Ares (a)" -> {
                if (to == "Acres (ac)") r = { it * 0.024710538146717 }
                if (to == "Hectares (ha)") r = { it * 0.01 }
                if (to == "Square centimeters (cm²)") r = { it * 1000000 }
                if (to == "Square feet (ft²)") r = { it * 1076.3915051182 }
                if (to == "Square inches (in²)") r = { it * 155000.31000062 }
                if (to == "Square meters (m²)") r = { it * 100 }

                return r
            }

            "Hectares (ha)" -> {
                if (to == "Acres (ac)") r = { it * 2.4710516301528 }
                if (to == "Ares (a)") r = { it * 100 }
                if (to == "Square centimeters (cm²)") r = { it * 100000000 }
                if (to == "Square feet (ft²)") r = { it * 107639.15051182 }
                if (to == "Square inches (in²)") r = { it * 15500031.000062 }
                if (to == "Square meters (m²)") r = { it * 10000 }

                return r
            }

            "Square centimeters (cm²)" -> {
                if (to == "Acres (ac)") r = { it * 2.4710516301528E-8 }
                if (to == "Ares (a)") r = { it * 1.0E-6 }
                if (to == "Hectares (ha)") r = { it * 1.0E-8 }
                if (to == "Square feet (ft²)") r = { it * 0.0010763915051182 }
                if (to == "Square inches (in²)") r = { it * 0.15500031000062 }
                if (to == "Square meters (m²)") r = { it * 0.0001 }

                return r
            }


            "Square feet (ft²)" -> {
                if (to == "Acres (ac)") r = { it / 43.560 }
                if (to == "Ares (a)") r = { it * 0.00092903 }
                if (to == "Hectares (ha)") r = { it * 9.2903E-6 }
                if (to == "Square centimeters (cm²)") r = { it * 929.0304 }
                if (to == "Square inches (in²)") r = { it * 143.99993799988 }
                if (to == "Square meters (m²)") r = { it * 0.092903 }

                return r
            }

            "Square inches (in²)" -> {
                if (to == "Acres (ac)") r = { it * 1.5942236697094E-7 }
                if (to == "Ares (a)") r = { it * 6.4516E-6 }
                if (to == "Hectares (ha)") r = { it * 6.4516E-8 }
                if (to == "Square centimeters (cm²)") r = { it * 6.4516 }
                if (to == "Square feet (ft²)") r = { it * 0.0069444474344208 }
                if (to == "Square meters (m²)") r = { it * 0.00064516 }

                return r
            }

            "Square meters (m²)" -> {
                if (to == "Acres (ac)") r = { it * 0.00024710516301528 }
                if (to == "Ares (a)") r = { it * 0.01 }
                if (to == "Hectares (ha)") r = { it * 0.0001 }
                if (to == "Square centimeters (cm²)") r = { it * 10000 }
                if (to == "Square inches (in²)") r = { it * 1550.0031000062 }
                if (to == "Square feet (ft²)") r = { it * 10.763915051182 }

                return r
            }

            else -> {
                return { it }
            }
        }
    }
}