import {
  BanknotesIcon,
  UserPlusIcon,
  UserIcon,
  ChartBarIcon,
} from "@heroicons/react/24/solid";

export const statisticsCardsData = [
  {
    color: "blue",
    icon: BanknotesIcon,
    title: "Vốn",
    value: "$53k",
    footer: {
      color: "text-green-500",
      value: "+55%",
      label: "so với hôm qua",
    },
  },
  {
    color: "pink",
    icon: UserIcon,
    title: "Người dùng hiện tại",
    value: "2,300",
    footer: {
      color: "text-green-500",
      value: "+3%",
      label: "so với tháng trước",
    },
  },
  {
    color: "green",
    icon: UserPlusIcon,
    title: "Lượt xem mới",
    value: "3,462",
    footer: {
      color: "text-red-500",
      value: "-2%",
      label: "so với hôm qua",
    },
  },
  {
    color: "orange",
    icon: ChartBarIcon,
    title: "Doanh thu",
    value: "$103,430",
    footer: {
      color: "text-green-500",
      value: "+5%",
      label: "so với hôm qua",
    },
  },
];

export default statisticsCardsData;
