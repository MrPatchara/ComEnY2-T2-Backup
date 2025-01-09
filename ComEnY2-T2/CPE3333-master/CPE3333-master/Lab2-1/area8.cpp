#include <print>
#include <cmath>
#include <stdfloat>
#include <version>

int main()
{	
	#if __cplusplus < 202302L
		#error "This program require gcc 14 or greater to run. (The program uses C++23)"
	#endif

    #ifdef __cpp_lib_print
        #if __cpp_lib_print < 202207L
            #error "std::println() doesn't support Formatted output"
        #endif
    #else
        #error "No support for std::println()"
    #endif

    #if __STDCPP_FLOAT128_T__ != 1
        #error "128-bit float type required"
    #endif

	std::println("Enter the radius and length of a cylinder:");
	std::float128_t radius, length;
	std::scanf("%Lf %Lf", &radius, &length);
	
	std::float128_t area = radius * radius * M_PIf128;
	std::float128_t volume = area * length;
	
	std::println("The area is {}", area);
	std::println("The volume is {}", volume);
	return 0;	
}
