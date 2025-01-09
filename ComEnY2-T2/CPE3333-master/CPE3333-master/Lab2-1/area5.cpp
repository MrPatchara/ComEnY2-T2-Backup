#include <iostream>
#include <cmath>
#include <stdfloat>
#include <version>

int main()
{	
	#if __cplusplus < 202302L
		#error "This program require gcc 14 or greater to run. (The program uses C++23)"
	#endif

	std::println("Enter the radius and length of a cylinder:");
	std::float128_t radius, length;
	std::cin >> radius >> length;
	
	std::float128_t area = radius * radius * M_PIf128;
	std::float128_t volume = area * length;
	
	std::println("The area is {}"), area;
	std::println("The volume is {}"), volume;
	return 0;	
}
