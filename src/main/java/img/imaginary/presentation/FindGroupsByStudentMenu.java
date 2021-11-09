package img.imaginary.presentation;

import img.imaginary.service.GroupService;

public class FindGroupsByStudentMenu extends MenuReader implements Menu {

    private String descrition = "Find all groups with less or equals student count";
    private GroupService groupService;

    public FindGroupsByStudentMenu(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public String getDescription() {
        return descrition;
    }

    @Override
    public void execute() {
        int studentsCount = readInt("input students count");
        groupService.findGroupsWithLessOrEqualsStudents(studentsCount).forEach(out::println);
    }
}
