#include <iostream>

int main()
{
	std::cout << "Enter the radius and length of a cylinder:" << std::endl;
	double radius, length;
	std::cin >> radius >> length;
	
	double area = radius * radius * 3.14159;
	double volume = area * length;
	
	std::cout << "The area is " << area << std::endl;
	std::cout << "The volume is " << volume << std::endl;
	return 0;	
}
