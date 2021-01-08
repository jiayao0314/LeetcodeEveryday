// 1087. Brace Expansion/m

public String[] expand(String S) {
    String[] arr = S.split("\\{|\\}");
    List<String> res = new ArrayList<>();
    dfs(arr, 0, "", res);
    return res.toArray(new String[res.size()]);
}

private void dfs(String[] arr, int idx, String word, List<String> res) {
    if(idx == arr.length) {
        res.add(word);
        return;
    }
    String[] group = arr[idx].split(",");
    Arrays.sort(group);
    for(String c: group) {
        dfs(arr, idx + 1, word + c, res);
    }
}