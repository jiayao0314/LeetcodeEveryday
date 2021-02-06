// 690. Employee Importance/e

// [[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]], 1  --> 13

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;  // is Integer here!
};
*/

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for(Employee e: employees) {
            map.put(e.id, e);
        }
        int total = 0;
        Queue<Employee> q = new LinkedList<>();
        q.offer(map.get(id));
        while(!q.isEmpty()) {
            Employee cur = q.poll();
            total += cur.importance;
            for(int subId: cur.subordinates) {
                q.offer(map.get(subId));
            }
        }
        return total;
    }
}