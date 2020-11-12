// 1125. Smallest Sufficient Team/h 
// Time O(people * 2^skill), Space O(2^skill)


class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int ns = req_skills.length, np = people.size();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < ns; ++i) map.put(req_skills[i], i);
        // skillGroup 是 2^ns 种技能组合（空集也包括在内，很重要！），idx代表技能组合的值。arr最后一位就是满技能，所以 res 是 skillGroup[(1 << ns) - 1]
        List<Integer>[] skillGroup = new List[1 << ns];
        skillGroup[0] = new ArrayList<>();
        for (int i = 0; i < np; ++i) {
            int skill = 0;
            // 遍历每个人计算他的技能值（算出来的其实是 skillGroup 里的 idx）
            for (String s : people.get(i)) skill |= (1 << map.get(s));
            for (int prev = 0; prev < skillGroup.length; ++prev) {
                // 为 null 则说明目前没有组合能达到这个值
                if (skillGroup[prev] == null) continue;
                int comb = prev | skill;
                // prev为0时，skillGroup是[ ]不是null！所以能计算comb，是当前这个人自己一组能获得的分。后半部分没想明白……
                if (skillGroup[comb] == null || skillGroup[prev].size() + 1 < skillGroup[comb].size()) {
                    skillGroup[comb] = new ArrayList<>(skillGroup[prev]);
                    skillGroup[comb].add(i);
                }
                // 因为题目说一定有这个高效的技能组合，所以在一开始设置 skillGroup size 的时候，直接把爆技能的可能删去了
            }
        }
        List<Integer> res = skillGroup[(1 << ns) - 1];
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; ++i) arr[i] = res.get(i);
        return arr;
    }
} 