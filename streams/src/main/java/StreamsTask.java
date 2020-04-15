import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTask {
    public static void main(String[] args) {

        Employee _1 = new Employee(1000, "Moscow", 20);
        Employee _2 = new Employee(2000, "Moscow", 21);
        Employee _3 = new Employee(3000, "Moscow", 22);
        Employee _4 = new Employee(4000, "Minks", 21);
        Employee _5 = new Employee(2000, "Minks", 20);
        Employee _6 = new Employee(1000, "Minks", 22);
        Employee _7 = new Employee(5000, "Kiev", 22);
        Employee _8 = new Employee(2000, "Kiev", 21);
        Employee _9 = new Employee(1000, "Kiev", 20);

        Collection<List<Employee>> employeesByDepartment = ImmutableSet.of(_1, _2, _3, _4, _5, _6, _7, _8, _9)
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .values();

        //expected output 12000
        System.out.println(findTotalSalaryOfEmployeesBelow22(employeesByDepartment));
    }

    /**
     * Returns total salary of all employees younger than 22, if no such employees throws RuntimeException
     * @param employeesByDepartment
     * @return
     */
    private static Integer findTotalSalaryOfEmployeesBelow22(Collection<List<Employee>> employeesByDepartment) {
        //implement me
        int sum = 0;
        List<Employee> listEmployeeAll = new ArrayList<Employee>();
        for (List<Employee> employeesByDepartmentItem: employeesByDepartment) {
            listEmployeeAll.addAll(employeesByDepartmentItem);
        }
        listEmployeeAll.stream().filter(x-> x.getAge() < 22).findAny().
                orElseThrow(RuntimeException::new);
        sum = listEmployeeAll.stream().filter(x-> x.getAge() < 22)
                .collect(Collectors.summingInt(Employee::getSalary));
        return sum;
    }
}
